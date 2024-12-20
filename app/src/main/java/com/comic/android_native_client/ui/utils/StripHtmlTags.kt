package com.comic.android_native_client.ui.utils

fun stripHtmlTags(input: String): String {
    return input.replace(Regex("<[^>]*>"), "")
}