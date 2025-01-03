package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.common.Identifiable
import com.comic.android_native_client.data.model.Comic
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
open class ComicResponse(
    override val id: String,
    val name: String,
    val slug: String,
    val status: String,
    val alternativeNames: List<String> = emptyList(),
    val thumbnailUrl: String = "",
    val authors: List<PersonResponse> = emptyList(),
    val artists: List<PersonResponse> = emptyList(),
    val categories: List<ComicCategoryResponse> = emptyList(),
    val tags: List<String> = emptyList(),
    val newChapters: List<ShortInfoChapterResponse> = emptyList(),
    val originalSource: OriginalSourceResponse,
    val characters: List<PersonResponse> = emptyList(),
    val newChapterUpdatedAt: Instant

) : Identifiable<String>


fun ComicResponse.toComic(): Comic {
    return Comic(
        id = id,
        name = name,
        slug = slug,
        status = status,
        alternativeNames = alternativeNames,
        thumbnailUrl = thumbnailUrl,
        authors = authors.map { it.toPerson() },
        artists = artists.map { it.toPerson() },
        categories = categories.map { it.toComicCategory() },
        tags = tags,
//        newChapters = emptyList(),
        originalSource = originalSource.toOriginalSource(),
        newChapterUpdatedAt = newChapterUpdatedAt,
        characters = characters.map { it.toPerson() }
    )
}