package com.comic.android_native_client.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class JwtTokenResponse(
    @SerialName("refreshToken") val refreshToken: String,
    @SerialName("accessToken") val accessToken: String
) {
    override fun toString(): String {
        return "JwtTokenResponse(refreshToken='$refreshToken', accessToken='$accessToken')"
    }
}
