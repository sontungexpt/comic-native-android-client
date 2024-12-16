package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.repository.CommentRepository
import com.comic.android_native_client.network.services.CommentService

class CommentRepositoryImpl(
    private val commentService: CommentService
) : CommentRepository {
    override suspend fun getTopLevelComments(comicId: Int): Page<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopLevelReplies(commentId: Int): Page<Comment> {
        TODO("Not yet implemented")
    }


    override suspend fun addComment(comicId: Int, comment: Comment): Comment {
        return comment
    }
}