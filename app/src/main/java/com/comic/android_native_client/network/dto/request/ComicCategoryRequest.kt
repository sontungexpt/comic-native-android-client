package com.comic.android_native_client.network.dto.request

import kotlinx.serialization.Serializable


@Serializable
data class ComicCategoryRequest(
    val name: String,
    val imageUrl: String,
    val description: String,
)
