package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
sealed class ResourceInfo {
    @Serializable
    object Absolute : ResourceInfo()

    @Serializable
    data class Relative(val baseUrl: String) : ResourceInfo()
}