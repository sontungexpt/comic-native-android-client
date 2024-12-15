package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.network.dto.response.UserInfoResponse
import com.comic.android_native_client.network.services.UserService
import com.comic.android_native_client.network.services.UserServiceAuthenticated
import retrofit2.Response
import retrofit2.Retrofit

class UserServiceImpl(
    private val authenticatedRetrofit: Retrofit,
    private val jwtRepository: JwtRepository,
) : UserService {

    private val userServiceAuthenticated by lazy {
        authenticatedRetrofit.create(UserServiceAuthenticated::class.java)
    }

    override suspend fun fetchUserInfo(): Response<UserInfoResponse> {
        val accessToken = jwtRepository.getAccessJwt()
        if (accessToken.isNullOrEmpty()) {
            throw Exception("Access token is empty")
        }
        return userServiceAuthenticated.fetchUserInfo()
    }
}