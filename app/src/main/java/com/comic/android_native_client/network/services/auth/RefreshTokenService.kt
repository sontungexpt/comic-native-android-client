package com.comic.android_native_client.network.services.auth

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.JwtTokenResponse
import retrofit2.Response
import retrofit2.http.POST

interface RefreshTokenService {
    @POST(MainEndpoint.REFRESH_TOKEN_V1)
    suspend fun refreshToken(): Response<JwtTokenResponse>
}