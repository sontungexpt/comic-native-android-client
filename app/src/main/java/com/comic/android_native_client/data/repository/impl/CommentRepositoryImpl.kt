package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.repository.CommentRepository
import com.comic.android_native_client.network.dto.request.CommentRequest
import com.comic.android_native_client.network.dto.response.toComment
import com.comic.android_native_client.network.services.CommentService
import com.comic.android_native_client.network.services.toPage

class CommentRepositoryImpl(
    private val commentService: CommentService
) : CommentRepository {
    override suspend fun getTopLevelComments(
        comicId: String,
        chapterId: String,
        parentId: String?,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Result<Page<Comment>> {
        val response = commentService.getTopLevelComments(
            comicId = comicId,
            chapterId = chapterId,
            parentId = parentId,
            page = page,
            size = size,
            sort = sort
        )

        when {
            response.isSuccessful -> {
                val body = response.body()
                return if (body != null) {
                    Result.Success(body.toPage { it.toComment() })
                } else {
                    Result.Error("Body is null")
                }
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }

        }
    }

    override suspend fun getTopLevelReplies(
        parentId: String?,
        page: Int,
        size: Int,
        sort: Array<String>?
    ): Result<Page<Comment>> {
        val response = commentService.getTopLevelReplies(
            sort = arrayOf("updatedAt"),
            page = page,
            size = size,
            parentId = parentId
        )

        when {
            response.isSuccessful -> {
                val body = response.body()
                return if (body != null) {
                    Result.Success(body.toPage { it.toComment() })
                } else {
                    Result.Error("Body is null")
                }
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }

        }
    }

    override suspend fun addComment(comment: CommentRequest): Result<Comment> {
        val response = commentService.addComment(comment)

        when {
            response.isSuccessful -> {
                val body = response.body()
                return if (body != null) {
                    Result.Success(body.toComment())
                } else {
                    Result.Error("Body is null")
                }
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }

        }
    }


}