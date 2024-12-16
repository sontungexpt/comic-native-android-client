package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private var _totalComics: Int = 0
    private var _currentPage: Int = -1


    var _favoriteComics = mutableStateListOf<Comic>()
    val favoriteComics: List<Comic>
        get() = _favoriteComics

    private var _nextPageFetching = mutableStateOf(false)
    val nextPageFetching
        get() = _nextPageFetching.value

    private val mutex = Mutex()

    val intialized: Boolean
        get() = _currentPage != -1

    fun hasNextPage(): Boolean = _currentPage == -1
            || _totalComics > _currentPage

    fun loadMoreComics() {
        viewModelScope.launch {
            mutex.withLock {
                if (_nextPageFetching.value ||
                    !hasNextPage()
                ) return@withLock
                _nextPageFetching.value = true
                val nextPage = _currentPage + 1
                when (val result = favoriteRepository.getFavoriteComics(
                    page = nextPage,
                    size = 10,
                    sort = arrayOf("createdAt,desc")
                )) {
                    is Result.Success -> {
                        val page = result.data
                        _totalComics = page.totalElements.takeIf { _currentPage == -1 }
                            ?: _totalComics
                        _currentPage = nextPage
                        _favoriteComics.addAll(page.content)
                    }

                    is Result.Error -> {}
                    is Result.Exception -> {}
                    else -> {}
                }
                _nextPageFetching.value = false
            }
        }
    }


    fun favoriteComic(comic: Comic) {
        viewModelScope.launch {
            when (val result = favoriteRepository.addFavorite(comic.id)) {
                is Result.Success -> {
                    _totalComics++
                    _favoriteComics.add(comic)
                }

                is Result.Error -> {}
                is Result.Exception -> {}
                else -> {}
            }
        }
    }

    fun unfavoriteComic(comic: Comic) {
        viewModelScope.launch {
            when (val result = favoriteRepository.removeFavorite(comic.id)) {
                is Result.Success -> {
                    _totalComics--
                    _favoriteComics.remove(comic)
                }

                is Result.Error -> {}
                is Result.Exception -> {}
                else -> {}
            }
        }
    }
}
