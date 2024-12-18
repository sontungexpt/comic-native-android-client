package com.comic.android_native_client.common

import com.comic.android_native_client.constants.HttpStatus


sealed class HttpResult<out T> {
    data class Success<T>(val data: T) : HttpResult<T>()
    data class Error(val message: String, val status: HttpStatus? = null) : HttpResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[message=$message,  status=$status]"
        }
    }
}