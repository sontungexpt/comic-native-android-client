package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.network.dto.request.CommentRequest

interface CommentRepository {

    suspend fun getTopLevelComments(
        comicId: String,
        chapterId: String,
        parentId: String? = null,
        page: Int,
        size: Int = 10,
        sort: Array<String> = arrayOf("updatedAt")
    ): HttpResult<Page<Comment>>

    suspend fun getTopLevelReplies(
        parentId: String? = null,
        page: Int,
        size: Int = 10,
        commentId: String
    ): HttpResult<Page<Comment>>

    suspend fun addComment(comicId: String, comment: CommentRequest): HttpResult<Comment>
}