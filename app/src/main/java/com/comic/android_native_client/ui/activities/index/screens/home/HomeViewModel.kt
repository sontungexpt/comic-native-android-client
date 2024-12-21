package com.comic.android_native_client.ui.activities.index.screens.home

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ComicsState(
    var loading: Boolean = true,
    val comics: List<Comic> = emptyList()
)

const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {
    val OUTSTANDING_KEY = "Outstanding"


    private var _comicsMap = mutableStateMapOf<String, ComicsState>()
    val comicsMap: Map<String, ComicsState>
        get() = _comicsMap


    fun initComics(
        genreIds: List<String>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                loadOutstandingComics()
            }
            genreIds.forEach { genreId ->
                launch {
                    loadGenreComics(genreId)
                }
            }
        }
    }

    private suspend fun loadOutstandingComics() {
        loadComics(OUTSTANDING_KEY) {
            comicRepository.getComics(
                page = 0,
                size = 10
            )
        }
    }

    private suspend fun loadGenreComics(genreId: String) {
        loadComics(genreId) {
            comicRepository.getComics(
                filterCategoryIds = listOf(genreId),
                page = 0,
                size = 10
            )
        }
    }

    private suspend fun loadComics(
        key: String,
        fetch: suspend () -> Result<Page<Comic>>
    ) {
        _comicsMap[key] = ComicsState(loading = true)
        try {
            when (val result = fetch()) {
                is Result.Success -> {
                    _comicsMap[key] = ComicsState(
                        loading = false,
                        comics = result.data.content
                    )
                }

                is Result.Error -> {
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _comicsMap[key] = _comicsMap[key]?.copy(loading = false) ?: ComicsState(
                loading = false,
            )
        }
    }
}