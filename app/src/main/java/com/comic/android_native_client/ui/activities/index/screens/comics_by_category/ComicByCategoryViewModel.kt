package com.comic.android_native_client.ui.activities.index.screens.comics_by_category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicByCategoryViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {
    private var _currPage = -1
    private var _lastPageReached = false

    private var _comics = mutableStateListOf<Comic>()
    private var _loading by mutableStateOf(false)

    val comics: List<Comic> get() = _comics
    val loading: Boolean get() = _loading
    val lastPageReached get() = _lastPageReached

    fun resetState() {
        _currPage = -1
        _lastPageReached = false
        _comics.clear()
    }

    fun fetchNextPage(categoryId: String) {
        if (_loading || _lastPageReached) return
        _loading = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = comicRepository.getComics(
                    filterCategoryIds = listOf(categoryId),
                    page = _currPage,
                )) {
                    is Result.Success -> {
                        val resultComics = result.data.content
                        if (resultComics.isEmpty()) {
                            _lastPageReached = true
                        } else {
                            val set = _comics.toMutableSet()
                            set.addAll(resultComics)
                            _comics.clear()
                            _comics.addAll(set)
                            _currPage++
                        }
                    }

                    is Result.Error -> {
                        // Handle error
                    }

                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _loading = false
            }
        }
    }
}
