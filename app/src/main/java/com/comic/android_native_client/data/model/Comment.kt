package com.comic.android_native_client.data.model

import com.comic.android_native_client.common.Identifiable
import com.comic.android_native_client.serialization.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant


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

    @Serializable(with = InstantSerializer::class)
    val updatedAt: Instant,

    val replies: List<Comment> = emptyList(),

    val author: CommentAuthor

) : Identifiable<String>

