package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.data.model.Person
import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val name: String,
    val description: String = "",
    val imageUrl: String = ""
)

fun PersonResponse.toPerson() = Person(
    name, description, imageUrl
)