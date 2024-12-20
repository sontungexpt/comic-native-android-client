package com.comic.android_native_client.ui.activities.index.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.ComicDetail
import com.comic.android_native_client.data.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "ComicDetailViewModel"

data class ComicDetailUIState(
    val initializing: Boolean = false,
    val comicDetail: ComicDetail? = null,
    val chapters: List<Chapter> = emptyList(),
    val loadingMoreChapter: Boolean = false
)

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {
    private var _currentChapterPage = 0
    private var _last = false

    val noMoreChapter: Boolean
        get() = _last

    private var _comicDetailUIState: MutableStateFlow<ComicDetailUIState> =
        MutableStateFlow(ComicDetailUIState())
    val comicDetailUIState: StateFlow<ComicDetailUIState> = _comicDetailUIState.asStateFlow()


    fun updateFavoriteStatus(
        status: Boolean,
        addFavoriteReqeust: (comic: Comic) -> Unit,
        removeFavoriteRequest: (comic: Comic) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_comicDetailUIState.value.comicDetail == null) return@launch
            _comicDetailUIState.value.comicDetail?.followed = status
            if (status) {
                addFavoriteReqeust(comicDetailUIState.value.comicDetail!!)
            } else {
                removeFavoriteRequest(comicDetailUIState.value.comicDetail!!)
            }
            _comicDetailUIState.value = comicDetailUIState.value.copy(
                comicDetail = _comicDetailUIState.value.comicDetail
            )
        }
    }

    fun loadMoreChapter() {
        if (_comicDetailUIState.value.comicDetail == null) return
        else if (_comicDetailUIState.value.loadingMoreChapter) return
        else if (_last) return
        _comicDetailUIState.value = _comicDetailUIState.value.copy(
            loadingMoreChapter = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nextPage = _currentChapterPage + 1
                Log.d(TAG, "load more chapter page: $nextPage ")
                when (val result = comicRepository.getComicDetail(
                    comicId = _comicDetailUIState.value.comicDetail!!.id,
                    sourceName = _comicDetailUIState.value.comicDetail!!.originalSource.name,
                    page = nextPage,
                    size = 10,
                )) {
                    is Result.Success -> {
                        var newChapters = mutableListOf(_comicDetailUIState.value.chapters)
                        _comicDetailUIState.value = _comicDetailUIState.value.copy(
                            chapters = _comicDetailUIState.value.chapters + result.data.chapters.content,
                            loadingMoreChapter = false
                        )
                        _last = result.data.chapters.last
                        _currentChapterPage = nextPage
                        return@launch
                    }

                    is Result.Error -> {
                        when (result.status) {
                            else -> {
                            }
                        }
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _comicDetailUIState.value = _comicDetailUIState.value.copy(
                    loadingMoreChapter = false
                )
            }
        }
    }

    fun initialize(comicId: String, sourceName: String) {
        if (_comicDetailUIState.value.initializing) return
        _comicDetailUIState.value = _comicDetailUIState.value.copy(
            initializing = true,
            loadingMoreChapter = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = comicRepository.getComicDetail(
                    comicId = comicId,
                    sourceName = sourceName,
                    page = _currentChapterPage,
                    size = 10,
                )) {
                    is Result.Success -> {
                        _comicDetailUIState.value = ComicDetailUIState(
                            comicDetail = result.data,
                            initializing = false,
                            chapters = result.data.chapters.content,
                            loadingMoreChapter = false
                        )
                        _last = result.data.chapters.last
                        return@launch
                    }

                    is Result.Error -> when (result.status) {
                        else -> {
                        }
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _comicDetailUIState.value = comicDetailUIState.value.copy(
                    initializing = false,
                    loadingMoreChapter = false
                )
            }
        }
    }

}
