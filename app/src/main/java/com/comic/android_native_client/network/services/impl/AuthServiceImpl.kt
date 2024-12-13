package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.dto.request.RegisterRequest
import com.comic.android_native_client.network.dto.response.LoginResponse
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.network.services.AuthServicePublic
import com.comic.android_native_client.network.services.AuthServiceWithRefresh
import retrofit2.Response
import retrofit2.Retrofit

class AuthServiceImpl(
    private val refreshTokenClientRetrofit: Retrofit,
    private val publicClientRetrofit: Retrofit,
    private val jwtRpository: JwtRepository,
) : AuthService {

    private val authServicePublic: AuthServicePublic by lazy {
        publicClientRetrofit.create(AuthServicePublic::class.java)
    }

    private val authServiceWithRefresh: AuthServiceWithRefresh by lazy {
        refreshTokenClientRetrofit.create(AuthServiceWithRefresh::class.java)
    }

    override suspend fun login(body: LoginRequest): Response<LoginResponse> {
        val loginResponse = authServicePublic.login(body)

        if (loginResponse.isSuccessful) {
            loginResponse.body()?.jwt?.let { jwt ->
                jwtRpository.saveAccessJwt(jwt.accessToken)
                jwtRpository.saveRefreshJwt(jwt.refreshToken)
            }
        }
        return loginResponse
    }

    override suspend fun register(body: RegisterRequest): Response<LoginResponse> {
        return authServicePublic.register(body)
    }

    override suspend fun logout(): Response<Unit> {
        return authServiceWithRefresh.logout()
    }
}