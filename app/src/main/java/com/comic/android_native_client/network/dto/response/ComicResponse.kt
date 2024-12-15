package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.serialization.serializer.InstantSerializer
import com.comic.android_native_client.ui.components.common.SliceableItem
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

) : SliceableItem<String>
