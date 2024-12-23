package com.comic.android_native_client.network.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CommentRequest(
    val content: String,
    val replyingTo: String? = null,
    val chapterId: String,
    val comicId: String
)
