package com.comic.android_native_client.network.services

import kotlinx.serialization.Serializable


@Serializable
data class ComicCatergoryRequest(
    val name: String,
    val imageUrl: String,
    val description: String,
)
