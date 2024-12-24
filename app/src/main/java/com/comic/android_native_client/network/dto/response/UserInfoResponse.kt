package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val name: String,

    val email: String = "",

    val bio: String = "",

    val totalCreatedComics: Long,

    val avatar: String = "",

    val status: String,

    val roles: Set<String>,

    val enabled: Boolean,
)
