package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.ui.components.common.SliceableItem
import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    override val id: String,
    val imageUrl: String,
    val name: String,
    val alternativeNames: List<String> = emptyList(),
    val summary: String = "",
    val thumbnailUrl: String,
    val slug: String,
    val description: String = "",
    val rating: UInt,
    val status: String,
    val dailyViews: Int,
    val weeklyViews: Int,
    val monthlyViews: Int,
    val yearlyViews: Int,
//    val newChapters: List<Chapter>,
    val authors: List<String> = emptyList(),
    val artists: List<String> = emptyList(),
    val categoryIds: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
//    val lastNewChapterCheckAt: Instant,
//    val newChapterUpdatedAt: Instant,
    val ownerId: String,
) : SliceableItem<String>
