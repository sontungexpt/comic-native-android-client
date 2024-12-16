package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.data.model.ComicCategory
import kotlinx.serialization.Serializable

@Serializable
data class ComicCategoryResponse(
    val id: String,
    val name: String,
    val slug: String,
    val imageUrl: String = "",
    val description: String = "",
)

fun ComicCategoryResponse.toComicCategory() = ComicCategory(
    id = id,
    name = name,
    slug = slug,
    imageUrl = imageUrl,
    description = description,
)


