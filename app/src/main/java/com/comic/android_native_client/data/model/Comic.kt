package com.comic.android_native_client.data.model

import com.comic.android_native_client.ui.components.common.SliceableItem

data class Comic(
    override val id: String,
    val authors: List<String>,
    val imageUrl: String,
    val name: String,
    val description: String,
    val rating: UInt,
    val newChapters: List<Chapter>
) : SliceableItem<String>