package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.dto.request.RegisterRequest
import com.comic.android_native_client.network.dto.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServicePublic {
    @POST(MainEndpoint.LOGIN_V1)
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @POST(MainEndpoint.REGISTER_V1)
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<LoginResponse>

}

interface AuthServiceWithRefresh {
    @POST(MainEndpoint.LOGOUT_V1)
    suspend fun logout(): Response<Unit>
}

interface AuthService : AuthServicePublic, AuthServiceWithRefresh