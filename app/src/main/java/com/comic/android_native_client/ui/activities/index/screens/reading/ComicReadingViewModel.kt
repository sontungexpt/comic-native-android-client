package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.repository.ChapterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


data class ChapterUiScreenState(
    val chapterLoading: Boolean = false,
    val chapter: Chapter? = null,

    val hasPrev: Boolean = false,
    val hasNext: Boolean = false,
    val chapterList: List<Chapter> = emptyList(),
    val chapterListLoading: Boolean = false
)

@HiltViewModel
class ComicReadingViewModel @Inject constructor(
    private val chapterRepository: ChapterRepository,
) : ViewModel() {
    private var _currChapterIndex = -1

    private var _uiState = MutableStateFlow(ChapterUiScreenState())
    val uiState: StateFlow<ChapterUiScreenState> = _uiState

    fun loadLastestReadChapter(
        comicId: String,
        onNotFound: () -> Unit,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(chapterLoading = true) }
            try {
                when (val result = chapterRepository.getLastestReadChapterDetail(comicId)) {
                    is Result.Success -> {
                        val chapter = result.data.also {
                            if (_currChapterIndex == -1) {
                                updateChapterIndex(_uiState.value.chapterList, it.id)
                            }
                        }
                        _uiState.update {
                            it.copy(
                                chapter = chapter,
                                chapterLoading = false
                            )
                        }
                        onSuccess()
                        return@launch
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                withContext(Dispatchers.Main) {
                                    onNotFound()
                                }
                            }

                            HttpStatus.BadRequest -> {
                                withContext(Dispatchers.Main) {
                                    onNotFound()
                                }
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


    fun loadChapter(
        comicId: String,
        chapterId: String,
        onNotFound: () -> Unit,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(chapterLoading = true) }
            try {
                when (val result = chapterRepository.getChapter(comicId, chapterId)) {
                    is Result.Success -> {
                        val chapter = result.data.also {
                            if (_currChapterIndex == -1) {
                                updateChapterIndex(_uiState.value.chapterList, it.id)
                            }
                        }
                        _uiState.update {
                            it.copy(
                                chapter = chapter,
                                chapterLoading = false
                            )
                        }
                        onSuccess()
                        return@launch
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                withContext(Dispatchers.Main) {
                                    onNotFound()
                                }
                            }

                            HttpStatus.BadRequest -> {
                                withContext(Dispatchers.Main) {
                                    onNotFound()
                                }
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

    fun lazyLoadAllChapters(
        comicId: String,
        onNotFound: () -> Unit,
    ) {
        if (_uiState.value.chapterList.isEmpty()) {
            loadAllChapters(comicId, onNotFound)
        }
    }


    fun nextChapter(
        comicId: String,
        onNotFound: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            lazyLoadAllChapters(comicId, onNotFound)

            if (_uiState.value.chapterListLoading) return@launch
            else if (_currChapterIndex == -1 || _uiState.value.chapterList.isEmpty()) return@launch
            else if (_currChapterIndex + 1 >= _uiState.value.chapterList.size) {
                loadAllChapters(comicId, onNotFound)
                if (_currChapterIndex + 1 >= _uiState.value.chapterList.size) {
                    return@launch
                }
            }

            loadChapter(
                comicId = comicId,
                chapterId = _uiState.value.chapterList[_currChapterIndex + 1].id,
                onNotFound = onNotFound,
                onSuccess = {
                    _currChapterIndex++
                    _uiState.update {
                        it.copy(
                            hasNext = _currChapterIndex < _uiState.value.chapterList.size - 1,
                            hasPrev = _currChapterIndex > 0
                        )
                    }

                }

            )

        }
    }

    fun prevChapter(
        comicId: String,
        onNotFound: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            lazyLoadAllChapters(comicId, onNotFound)
            if (_uiState.value.chapterList.isEmpty()) return@launch
            else if (_currChapterIndex < 1) return@launch

            loadChapter(
                comicId = comicId,
                chapterId = _uiState.value.chapterList[_currChapterIndex - 1].id,
                onNotFound = onNotFound,
                onSuccess = {
                    _currChapterIndex--
                    _uiState.update {
                        it.copy(
                            hasNext = _currChapterIndex < _uiState.value.chapterList.size - 1,
                            hasPrev = _currChapterIndex > 0
                        )
                    }
                }
            )
        }
    }

    fun updateChapterIndex(chapterList: List<Chapter>, chapterId: String?) {
        if (chapterList.isNotEmpty() && chapterId != null) {
            _currChapterIndex = _uiState.value.chapterList.indexOfFirst { it.id == chapterId }
        }
    }

    fun loadAllChapters(
        comicId: String,
        onNotFound: () -> Unit
    ) {
        if (_uiState.value.chapterListLoading) return
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(chapterListLoading = true) }
            try {
                when (val result = chapterRepository.getAllChapters(comicId)) {
                    is Result.Success -> {
                        val chapterList = result.data.also {
                            updateChapterIndex(it, _uiState.value.chapter?.id)
                        }
                        _uiState.update {
                            it.copy(
                                chapterList = chapterList,
                                chapterListLoading = false,
                                hasNext = _currChapterIndex < chapterList.size - 1,
                                hasPrev = _currChapterIndex > 0
                            )
                        }
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                withContext(Dispatchers.Main) {
                                    onNotFound()
                                }
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
