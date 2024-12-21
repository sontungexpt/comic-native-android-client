package com.comic.android_native_client.ui.activities.index.screens.search

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicSearchViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {

    private var _currPage = -1
    private var _lastPageReached = false

    private var _foundComics = mutableStateListOf<Comic>()
    private var _searchQuery by mutableStateOf("")
    private var _loading by mutableStateOf(false)

    val loading: Boolean
        get() = _loading

    val searchQuery: String
        get() = _searchQuery

    val foundComics: List<Comic>
        get() = _foundComics

    fun updateSearchQuery(value: String) {
        _searchQuery = value
    }

    fun loadMore() {
        if (_lastPageReached || _loading) return
        fetchComics(reset = false)
    }

    fun initSearch() {
        if (_loading) return
        _lastPageReached = false
        fetchComics(reset = true)
    }

    private fun fetchComics(reset: Boolean) {
        _loading = true

        viewModelScope.launch {
            try {
                val pageToLoad = if (reset) 0 else _currPage + 1
                val result = comicRepository.searchComic(
                    _searchQuery,
                    page = pageToLoad,
                    size = 10
                )

                when (result) {
                    is Result.Success -> {
                        val comics = result.data.content
                        if (comics.isEmpty()) {
                            _lastPageReached = true
                        } else {
                            if (reset) _foundComics.clear()
                            _foundComics.addAll(comics)
                            _currPage = pageToLoad
                        }
                    }

                    is Result.Error -> {
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading = false
            }
        }
    }
}
