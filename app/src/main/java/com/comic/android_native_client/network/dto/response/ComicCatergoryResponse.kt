package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ComicCatergoryResponse(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val slug: String
)

@Serializable
class ComicCatergoriesResponse : ArrayList<ComicCatergoryResponse>()