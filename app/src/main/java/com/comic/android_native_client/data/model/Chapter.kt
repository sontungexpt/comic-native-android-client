package com.comic.android_native_client.data.model

import com.comic.android_native_client.network.dto.response.ResourceInfo

data class Chapter(
    val id: String = "",
    val comicId: String = "",
    val type: String = "",
    val thumbnailUrl: String = "",
    val num: Double = 0.0,
    val name: String = "",
    val description: String = "",
    val originalSource: ResourceInfo = ResourceInfo.Absolute(""),
    val updatedAt: String = ""
)