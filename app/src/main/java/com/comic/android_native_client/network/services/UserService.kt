package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.request.UpdateUserInfoRequest
import com.comic.android_native_client.network.dto.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH

interface AuthenticatedUserService {
    @GET(MainEndpoint.GET_USER_PROFILE_V1)
    suspend fun fetchUserInfo(): Response<UserInfoResponse>

    @PATCH(MainEndpoint.UPDATE_USER_PROFILE_V1)
    @Headers("Content-Type: application/merge-patch+json")
    suspend fun updateUserInfo(
        @Body updateUserInfoRequest: UpdateUserInfoRequest
    ): Response<UserInfoResponse>
}


interface UserService : AuthenticatedUserService
