package com.comic.shareable_theme.ui.theme.utils

import android.content.Context
import android.content.res.Configuration

fun isSystemInDarkTheme(context: Context): Boolean {
    val uiMode = context.resources.configuration.uiMode
    return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}