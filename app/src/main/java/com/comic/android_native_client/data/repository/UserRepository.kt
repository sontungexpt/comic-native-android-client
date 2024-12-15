package com.comic.android_native_client.data.repository

import com.comic.android_native_client.data.model.User

interface UserRepository {

    suspend fun getUser(): User?
}