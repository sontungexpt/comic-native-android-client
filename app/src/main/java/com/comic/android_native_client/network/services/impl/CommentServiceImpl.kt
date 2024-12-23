package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.network.dto.request.CommentRequest
import com.comic.android_native_client.network.dto.response.CommentResponse
import com.comic.android_native_client.network.services.AuthenticatedCommentService
import com.comic.android_native_client.network.services.CommentService
import com.comic.android_native_client.network.services.PageResponse
import com.comic.android_native_client.network.services.PublicCommentService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CommentServiceImpl @Inject constructor(
    private val authenticatedClientRetrofit: Retrofit,
    private val publicClientRetrofit: Retrofit
) : CommentService {
    private val authenticatedCommentService by lazy {
        authenticatedClientRetrofit.create(AuthenticatedCommentService::class.java)
    }

    private val publicCommentService by lazy {
        publicClientRetrofit.create(PublicCommentService::class.java)
    }

    override suspend fun getTopLevelComments(
        comicId: String,
        chapterId: String,
        parentId: String?,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<CommentResponse>> {
        return publicCommentService.getTopLevelComments(
            comicId = comicId,
            chapterId = chapterId,
            parentId = parentId,
            page = page,
            size = size,
            sort = sort
        )
    }

    override suspend fun getTopLevelReplies(
        parentId: String?,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<CommentResponse>> {
        return publicCommentService.getTopLevelReplies(
            parentId = parentId,
            page = page,
            size = size,
            sort = sort
        )
    }

    override suspend fun addComment(commentRequest: CommentRequest): Response<CommentResponse> {
        return authenticatedCommentService.addComment(commentRequest)
    }

}