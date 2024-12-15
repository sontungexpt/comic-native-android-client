package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
sealed class ResourceInfo {
    @Serializable
    data class Absolute(val url: String) : ResourceInfo()

    @Serializable
    data class Relative(val baseUrl: String, val path: String) : ResourceInfo()
}