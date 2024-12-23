package com.comic.android_native_client.ui.common

sealed class LoadableResult<out T> {
    data class Done<T>(val data: T) : LoadableResult<T>()
    object Loading : LoadableResult<Nothing>()
}