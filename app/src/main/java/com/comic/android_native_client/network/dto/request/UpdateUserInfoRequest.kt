package com.comic.android_native_client.network.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserInfoRequest(
    val name: String?,
    val email: String?,
    val bio: String?,
)