package com.comic.android_native_client.data.model

import com.comic.android_native_client.common.Identifiable
import kotlinx.datetime.Instant


data class CommentAuthor(
    val pubId: String,
    val name: String,
    val avatar: String = "",
) : Identifiable<String> {
    override val id: String
        get() = pubId
}

data class Comment(
    override val id: String,
    val parentId: String?,
    val comicId: String,
    val chapterId: String,

    val depth: Int,
    val totalReplies: Int,
    val content: String,

    val updatedAt: Instant,

    val replies: List<Comment> = emptyList(),

    val author: CommentAuthor

) : Identifiable<String>

