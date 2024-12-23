package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private var _totalComics: Int = 0
    private var _currentPage: Int = -1

    private var _favoriteComics = mutableStateListOf<Comic>()
    private var _loadingMore by mutableStateOf(false)
    private var _favoriteChanging by mutableStateOf(false)

    val comics: List<Comic>
        get() = _favoriteComics

    val loadingMore
        get() = _loadingMore

    val favoriteChanging
        get() = _favoriteChanging

    val intialized: Boolean
        get() = _currentPage != -1


    fun hasNextPage(): Boolean = !intialized
            || _totalComics > _favoriteComics.size

    fun loadMoreComics() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_loadingMore) return@launch
            else if (!hasNextPage()) return@launch
            _loadingMore = true
            val nextPage = _currentPage + 1
            try {
                when (val result = favoriteRepository.getFavoriteComics(
                    page = nextPage,
                    size = 10,
                )) {
                    is Result.Success -> {
                        val page = result.data
                        if (!intialized) _totalComics = page.totalElements
                        _currentPage = nextPage
                        _favoriteComics.addAll(page.content)
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {}
                            else -> {}
                        }
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loadingMore = false
            }
        }


    }


    fun favoriteComic(
        comic: Comic
    ) {
        _favoriteChanging = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = favoriteRepository.addFavorite(comic.id)) {
                    is Result.Success -> {
                        _totalComics++
                        _favoriteComics.add(comic)
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.Conflict -> {

                            }

                            else -> {}
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _favoriteChanging = false
            }

        }
    }

    fun unfavoriteComic(
        comic: Comic,
        onSuccess: () -> Unit = {},
        onError: (HttpStatus) -> Unit = {}
    ) {
        _favoriteChanging = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = favoriteRepository.removeFavorite(comic.id)) {
                    is Result.Success -> {
                        _totalComics--
                        _favoriteComics.remove(comic)
                        onSuccess()
                    }

                    is Result.Error -> {
                        when (result.status) {
                            else -> {

                            }
                        }
                        result.status?.let { onError(it) }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _favoriteChanging = false
            }

        }
    }
}
