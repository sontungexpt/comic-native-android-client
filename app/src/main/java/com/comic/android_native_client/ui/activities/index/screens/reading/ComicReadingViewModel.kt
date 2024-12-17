package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.repository.ChapterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ComicReadingViewModel @Inject constructor(
    private val chapterRepository: ChapterRepository
) : ViewModel() {
    private var _chapterDetail by mutableStateOf<Chapter?>(null)
    private var _loading by mutableStateOf(false)

    val chapterDetail: Chapter?
        get() = _chapterDetail

    val loading: Boolean
        get() = _loading

    fun loadChapter(
        comicId: String,
        chapterId: String,
        onNotFound: () -> Unit,
    ) {
        if (_loading) return
        _loading = true

        viewModelScope.launch {
            try {
                when (val result = chapterRepository.getChapter(comicId, chapterId)) {
                    is Result.Success -> {
                        _chapterDetail = result.data
                    }

                    is Result.Error -> {
                        when (result.status) {
                            HttpStatus.NotFound -> {
                                onNotFound()
                            }

                            HttpStatus.BadRequest -> {
                                onNotFound()
                            }

                            else -> {
                            }
                        }
                    }

                    else -> {
                        // Handle no content
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
