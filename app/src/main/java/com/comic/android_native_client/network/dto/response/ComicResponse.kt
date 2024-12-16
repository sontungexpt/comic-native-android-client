package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.common.Identifiable
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.serialization.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ComicResponse(
    override val id: String,
    val name: String,
    val slug: String,
    val status: String,
    val ownerId: String,
    val dailyViews: Int,
    val weeklyViews: Int,
    val monthlyViews: Int,
    val yearlyViews: Int,

    val alternativeNames: List<String> = emptyList(),
    val summary: String = "",
    val thumbnailUrl: String = "",
    val description: String = "",
//    val newChapters: List<Chapter>,

    val authors: List<String> = emptyList(),
    val artists: List<String> = emptyList(),
    val categoryIds: List<String> = emptyList(),
    val tags: List<String> = emptyList(),

    @Serializable(with = InstantSerializer::class)
    val lastNewChapterCheckAt: Instant,

    @Serializable(with = InstantSerializer::class)
    val newChapterUpdatedAt: Instant

) : Identifiable<String>


fun ComicResponse.toComic(): Comic {
    return Comic(
        id = id,
        name = name,
        slug = slug,
        status = status,
        ownerId = ownerId,
        dailyViews = dailyViews,
        weeklyViews = weeklyViews,
        monthlyViews = monthlyViews,
        yearlyViews = yearlyViews,
        alternativeNames = alternativeNames,
        summary = summary,
        thumbnailUrl = thumbnailUrl,
        description = description,
        authors = authors,
        artists = artists,
        categoryIds = categoryIds,
        tags = tags,
        lastNewChapterCheckAt = lastNewChapterCheckAt,
        newChapterUpdatedAt = newChapterUpdatedAt,
        newChapters = emptyList(),
    )
}