package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.common.Identifiable
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.CommentAuthor
import com.comic.android_native_client.serialization.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

data class CommentAuthorResponse(
    val pubId: String,
    val name: String,
    val avatar: String = "",
) : Identifiable<String> {
    override val id: String
        get() = pubId
}

fun CommentAuthorResponse.toCommentAuthor(): CommentAuthor = CommentAuthor(
    pubId = pubId,
    name = name,
    avatar = avatar
)

data class CommentResponse(
    override val id: String,
    val parentId: String?,
    val comicId: String,
    val chapterId: String,

    val depth: Int,
    val totalReplies: Int,
    val content: String,

    @Serializable(with = InstantSerializer::class)
    val updatedAt: Instant,

    val replies: List<CommentResponse> = emptyList(),

    val author: CommentAuthorResponse

) : Identifiable<String>

fun CommentResponse.toComment(): Comment = Comment(
    id = id,
    parentId = parentId,
    comicId = comicId,
    chapterId = chapterId,
    depth = depth,
    totalReplies = totalReplies,
    content = content,
    updatedAt = updatedAt,
    replies = replies.map { it.toComment() },
    author = author.toCommentAuthor()
)
