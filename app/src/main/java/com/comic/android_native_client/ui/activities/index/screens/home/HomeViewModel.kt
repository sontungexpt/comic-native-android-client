package com.comic.android_native_client.ui.activities.index.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ComicsState(
    var loading: Boolean = false,
    val comics: List<Comic>
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
        viewModelScope.launch {
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
        Log.d(TAG, "load Outstanding Comics")
        _comicsMap[OUTSTANDING_KEY] = ComicsState(
            loading = true,
            comics = emptyList()
        )
        try {
            val result = comicRepository.getComics(
                page = 0,
                size = 10,
            )
            when (result) {
                is Result.Success -> {
                    _comicsMap[OUTSTANDING_KEY] = ComicsState(
                        loading = false,
                        comics = result.data.content
                    )
                    return
                }

                is Result.Error -> {

                }

                else -> {

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _comicsMap[OUTSTANDING_KEY] =
                _comicsMap[OUTSTANDING_KEY]?.copy(loading = false) ?: ComicsState(
                    loading = false,
                    comics = emptyList()
                )
            Log.d(TAG, "load Outstanding Comics finally")
        }

    }

    private suspend fun loadGenreComics(genreId: String) {
        Log.d(TAG, "load Genre Comics $genreId")
        _comicsMap[genreId] = ComicsState(
            loading = true,
            comics = emptyList()
        )

        try {
            val result = comicRepository.getComics(
                filterCategoryIds = listOf(genreId),
                page = 0,
                size = 10,
            )
            when (result) {
                is Result.Success -> {
                    _comicsMap[genreId] = ComicsState(
                        loading = false,
                        comics = result.data.content
                    )
                    return
                }

                is Result.Error -> {

                }

                else -> {

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _comicsMap[genreId] =
                _comicsMap[genreId]?.copy(loading = false) ?: ComicsState(
                    loading = false,
                    comics = emptyList()
                )
            Log.d(TAG, "load Genre Comics $genreId finally")
        }
    }
}