package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.common.Identifiable
import com.comic.android_native_client.data.model.ComicDetail
import com.comic.android_native_client.network.services.PageResponse
import com.comic.android_native_client.network.services.toPage
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ComicDetailResponse(
    override val id: String,
    val name: String,
    val slug: String,
    val status: String,
    val summary: String = "",
    val alternativeNames: List<String> = emptyList(),
    val thumbnailUrl: String = "",
    val authors: List<PersonResponse> = emptyList(),
    val artists: List<PersonResponse> = emptyList(),
    val categories: List<ComicCategoryResponse> = emptyList(),
    val tags: List<String> = emptyList(),
    val newChapters: List<ShortInfoChapterResponse> = emptyList(),
    val originalSource: OriginalSourceResponse,
    val characters: List<PersonResponse> = emptyList(),
    val newChapterUpdatedAt: Instant,
    val chapters: PageResponse<ShortInfoChapterResponse>
) : Identifiable<String>

fun ComicDetailResponse.toComicDetail(): ComicDetail {
    return ComicDetail(
        id = id,
        name = name,
        slug = slug,
        status = status,
        summary = summary,
        alternativeNames = alternativeNames,
        thumbnailUrl = thumbnailUrl,
        authors = authors.map { it.toPerson() },
        artists = artists.map { it.toPerson() },
        categories = categories.map { it.toComicCategory() },
        tags = tags,
        newChapters = newChapters.map { it.toChapter() },
        originalSource = originalSource.toOriginalSource(),
        newChapterUpdatedAt = newChapterUpdatedAt,
        characters = characters.map { it.toPerson() },
        chapters = chapters.toPage { it.toChapter() }
    )
}