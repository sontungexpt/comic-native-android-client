package com.comic.android_native_client.data.repository.impl

import android.util.Log
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.User
import com.comic.android_native_client.data.repository.UserRepository
import com.comic.android_native_client.network.dto.request.UpdateUserInfoRequest
import com.comic.android_native_client.network.services.UserService

const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(): User? {
        try {
            val response = userService.fetchUserInfo()
            if (response.isSuccessful) {
                response.body()?.let {
                    return User(
                        name = it.name,
                        avatar = it.avatar,
                        bio = it.bio
                    )
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error fetching user info")
        }
        return null


    }

    override suspend fun updateUser(updateUserInfoRequest: UpdateUserInfoRequest): Result<User> {
        val response = userService.updateUserInfo(updateUserInfoRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(
                    User(
                        name = it.name,
                        avatar = it.avatar,
                        bio = it.bio
                    )
                )
            }
        }
        return Result.Error(response.message(), HttpStatus.from(response.code()))

    }
}