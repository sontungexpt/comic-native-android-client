package com.comic.android_native_client.common


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val code: Int? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object NoContent : Result<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[message=$message, code=$code]"
            Loading -> "Loading"
            NoContent -> "NoContent"
        }
    }
}