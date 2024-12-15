package com.comic.android_native_client.data.model

import com.comic.android_native_client.common.Identifiable

class ComicCategory(
    override val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val slug: String
) : Identifiable<String>