package com.comic.android_native_client.ui.common

sealed class UIResult<out T> {
    data class Success<T>(val data: T) : UIResult<T>()
    data class Error<T>(val message: String) : UIResult<T>()
    object Loading : UIResult<Nothing>()
    object Empty : UIResult<Nothing>()
}