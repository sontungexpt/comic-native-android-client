package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
open class ChapterResponse(
    open val id: String = "",
    open val comicId: String = "",
    open val type: String = "",
    open val thumbnailUrl: String = "",
    open val num: Double = 0.0,
    open val name: String = "",
    open val description: String = "",
    open val originalSource: ResourceInfo = ResourceInfo.Absolute(""),
    open val updatedAt: String = ""
)

//
//@Serializable
//data class ComicChapterResponse(
//    override val id: String,
//    override val type: String = "COMIC",
//    val sourceInfo: ResourceInfo,
//    val imagePages: List<ImagePage>
//) : ChapterResponse()
//
//
//@Serializable
//data class ImagePage(val number: Int, val path: String)
//
//@Serializable
//data class NovelChapterResponse(
//    override val id: String,
//    override val type: String = "NOVEL",
//    val content: String
//) : ChapterResponse()
//

