package com.comic.android_native_client.ui.activities.index.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.data.model.ComicDetail
import com.comic.android_native_client.data.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {
    private var _isLoading by mutableStateOf(false)
    val isLoading
        get() = _isLoading

    private var _comicDetail: MutableState<ComicDetail?> = mutableStateOf(null)
    val comicDetail: ComicDetail?
        get() = _comicDetail.value


    fun fetchComicDetail(comicId: String) {
        if (_isLoading) return
        _isLoading = true
        viewModelScope.launch(Dispatchers.IO) {


            _isLoading = false
        }


    }

}
