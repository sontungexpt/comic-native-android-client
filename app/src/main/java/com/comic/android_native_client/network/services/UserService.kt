package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserServiceAuthenticated {
    @GET(MainEndpoint.GET_USER_PROFILE_V1)
    suspend fun fetchUserInfo(): Response<UserInfoResponse>
}


interface UserService : UserServiceAuthenticated
