package com.comic.android_native_client.data.repository

import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.Page

interface CommentRepository {

    suspend fun getTopLevelComments(comicId: Int): Page<Comment>

    suspend fun getTopLevelReplies(commentId: Int): Page<Comment>

    suspend fun addComment(comicId: Int, comment: Comment): Comment
}