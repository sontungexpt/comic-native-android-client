package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.User
import com.comic.android_native_client.network.dto.request.UpdateUserInfoRequest

interface UserRepository {

    suspend fun getUser(): User?

    suspend fun updateUser(updateUserInfoRequest: UpdateUserInfoRequest): Result<User>
}