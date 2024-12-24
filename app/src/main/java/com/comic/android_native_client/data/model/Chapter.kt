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
    val chapter: String = "",
    val read: Boolean = false,
    val originalSource: OriginalSource,
    val updatedDate: Instant?,
)


class ComicChapter(
    id: String = "",
    comicId: String = "",
    type: String = "",
    thumbnailUrl: String = "",
    chapter: String = "",
    num: Double = 0.0,
    name: String = "",
    read: Boolean = false,
    description: String = "",
    originalSource: OriginalSource,
    updatedDate: Instant?,
    val imagePages: List<ImagePage>,
    val resourceInfo: ResourceInfo
) : Chapter(
    id,
    comicId,
    type,
    thumbnailUrl,
    num,
    name,
    description,
    chapter,
    read,
    originalSource,
    updatedDate
)

class NovelChapter(
    id: String = "",
    comicId: String = "",
    type: String = "",
    read: Boolean = false,
    thumbnailUrl: String = "",
    num: Double = 0.0,
    name: String = "",
    description: String = "",
    chapter: String = "",
    originalSource: OriginalSource,
    updatedDate: Instant?,
    val content: String
) : Chapter(
    id = id,
    comicId = comicId,
    type = type,
    thumbnailUrl = thumbnailUrl,
    num = num,
    name = name,
    description = description,
    chapter = chapter,
    read = read,
    originalSource = originalSource,
    updatedDate = updatedDate
)