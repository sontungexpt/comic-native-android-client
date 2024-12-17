package com.comic.android_native_client.common

import com.comic.android_native_client.constants.HttpStatus


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val status: HttpStatus? = null) : Result<Nothing>()
    object NoContent : Result<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[message=$message,  status=$status]"
            NoContent -> "NoContent"
        }
    }
}