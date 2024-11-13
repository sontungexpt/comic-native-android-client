package com.comic.android_native_client.data.model

data class Comic(
    val authors: List<String>,
    val imageUrl: String,
    val name: String,
    val description: String,
    val rating: UInt,
    val newChapters: List<Chapter>
)