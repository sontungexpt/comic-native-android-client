package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.network.dto.request.UpdateUserInfoRequest
import com.comic.android_native_client.network.dto.response.UserInfoResponse
import com.comic.android_native_client.network.services.AuthenticatedUserService
import com.comic.android_native_client.network.services.UserService
import retrofit2.Response
import retrofit2.Retrofit

class UserServiceImpl(
    private val authenticatedRetrofit: Retrofit,
    private val jwtRepository: JwtRepository,
) : UserService {

    private val userServiceAuthenticated by lazy {
        authenticatedRetrofit.create(AuthenticatedUserService::class.java)
    }

    override suspend fun fetchUserInfo(): Response<UserInfoResponse> {
        return userServiceAuthenticated.fetchUserInfo()
    }

    override suspend fun updateUserInfo(updateUserInfoRequest: UpdateUserInfoRequest): Response<UserInfoResponse> {
        return userServiceAuthenticated.updateUserInfo(updateUserInfoRequest)
    }
}