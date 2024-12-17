package com.comic.android_native_client.data.model

import com.comic.android_native_client.network.dto.response.ResourceInfo
import kotlinx.datetime.Instant

open class Chapter(
    val id: String = "",
    val comicId: String = "",
    val type: String = "",
    val thumbnailUrl: String = "",
    val num: Double = 0.0,
    val name: String = "",
    val description: String = "",
    val originalSource: OriginalSource,
    val updatedDate: Instant?
)


class ComicChapter(
    id: String = "",
    comicId: String = "",
    type: String = "",
    thumbnailUrl: String = "",
    num: Double = 0.0,
    name: String = "",
    description: String = "",
    originalSource: OriginalSource,
    updatedDate: Instant?,
    val imagePages: List<ImagePage>,
    val resourceInfo: ResourceInfo
) : Chapter(id, comicId, type, thumbnailUrl, num, name, description, originalSource, updatedDate)

class NovelChapter(
    id: String = "",
    comicId: String = "",
    type: String = "",
    thumbnailUrl: String = "",
    num: Double = 0.0,
    name: String = "",
    description: String = "",
    originalSource: OriginalSource,
    updatedDate: Instant?,
    val content: String
) : Chapter(id, comicId, type, thumbnailUrl, num, name, description, originalSource, updatedDate)