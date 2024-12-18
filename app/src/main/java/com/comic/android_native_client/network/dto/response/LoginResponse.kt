package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("jwt")
    val jwt: JwtTokenResponse,

    @SerialName("name")
    val name: String,

    val avatar: String = ""
) {
    override fun toString(): String {
        return "LoginResponse(jwt=$jwt, name='$name', avatar='$avatar')"
    }
}
