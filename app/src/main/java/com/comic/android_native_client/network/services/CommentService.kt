package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.request.CommentRequest
import com.comic.android_native_client.network.dto.response.CommentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicCommentService {

    @GET(MainEndpoint.GET_TOP_LEVEL_COMMENTS_V1)
    suspend fun getTopLevelComments(
        @Query("comicId") comicId: String,
        @Query("chapterId") chapterId: String,
        @Query("parentId") parentId: String? = null,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Array<String> = arrayOf("updatedAt"),
    ): Response<PageResponse<CommentResponse>>

    @GET(MainEndpoint.GET_TOP_LEVEL_REPLIES_V1)
    suspend fun getTopLevelReplies(
        @Path("commentId") parentId: String? = null,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Array<String> = arrayOf("updatedAt"),
    ): Response<PageResponse<CommentResponse>>

    // @PUT(MainEndpoint.UPDATE_COMMENT_V1)
    // suspend fun updateComment(
    //     @Path("commentId") commentId: String,
    //     @Body request: UpdateCommentRequest
    // ): Response<CommentResponse>

    // @DELETE(MainEndpoint.DELETE_COMMENT_V1)
    // suspend fun deleteComment(
    //     @Path("commentId") commentId: String,
    // ): Response<CommentResponse>
}

interface AuthenticatedCommentService {

    @POST(MainEndpoint.ADD_COMMENT_V1)
    suspend fun addComment(
        @Body commentRequest: CommentRequest
    ): Response<CommentResponse>

}


interface CommentService : PublicCommentService, AuthenticatedCommentService