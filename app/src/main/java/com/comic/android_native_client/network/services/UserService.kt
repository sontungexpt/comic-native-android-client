package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.dto.response.UserNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("office/{officeId}/users/{userId}")
    suspend fun fetchUser(
        @Path("officeId") officeId: Long,
        @Path("userId") userId: Long
    ): Response<UserNetworkResponse>
}

