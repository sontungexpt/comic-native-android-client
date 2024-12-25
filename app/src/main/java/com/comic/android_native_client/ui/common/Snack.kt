package com.comic.android_native_client.ui.common

import androidx.compose.runtime.mutableStateOf

class Snack(
    val imageUrl: String
) {
    var zoom = mutableStateOf(1f)
}