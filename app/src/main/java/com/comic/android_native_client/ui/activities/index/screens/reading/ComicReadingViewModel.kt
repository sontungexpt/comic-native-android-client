package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.repository.ChapterRepository
import com.comic.android_native_client.data.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ChapterUiScreenState(
    val chapterLoading: Boolean = false,
    val chapter: Chapter? = null,

    val comments: Map<String, Comment> = emptyMap(),
    val commentLoading: Boolean = false,
    val topLevelCommentIds: List<String> = emptyList(),

    val chapterList: List<Chapter> = emptyList(),
    val chapterListLoading: Boolean = false
)

@HiltViewModel
class ComicReadingViewModel @Inject constructor(
    private val chapterRepository: ChapterRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(ChapterUiScreenState())
    val uiState: StateFlow<ChapterUiScreenState> = _uiState

    fun loadChapter(
        comicId: String,
        chapterId: String,
        onNotFound: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(chapterLoading = true) }
            try {
                when (val result = chapterRepository.getChapter(comicId, chapterId)) {
                    is HttpResult.Success -> {
                        _uiState.update {
                            it.copy(
                                chapter = result.data,
                                chapterLoading = false
                            )
                        }
                        return@launch
                    }

                    is HttpResult.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                onNotFound()
                            }

                            HttpStatus.BadRequest -> {
                                onNotFound()
                            }

                            else -> TODO()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _uiState.update { it.copy(chapterLoading = false) }
            }

        }
    }

    fun loadAllChapters(
        comicId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(chapterListLoading = true) }
            try {
                when (val result = chapterRepository.getAllChapters(comicId)) {
                    is HttpResult.Success -> {
                        _uiState.update {
                            it.copy(
                                chapterList = result.data,
                                chapterListLoading = false
                            )
                        }
                    }

                    is HttpResult.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                // Handle not found
                            }

                            HttpStatus.BadRequest -> {
                                // Handle bad request
                            }

                            else -> {
                                // Handle other errors
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _uiState.update { it.copy(chapterListLoading = false) }
            }
        }
    }
}
