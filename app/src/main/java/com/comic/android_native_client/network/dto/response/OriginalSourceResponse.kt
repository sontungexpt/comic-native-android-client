package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.data.model.OriginalSource
import kotlinx.serialization.Serializable


@Serializable
data class OriginalSourceResponse(
    val name: String,
    val description: String = "",
    val link: String = ""
)

fun OriginalSourceResponse.toOriginalSource(): OriginalSource {
    return OriginalSource(
        name = name,
        description = description,
        link = link
    )
}