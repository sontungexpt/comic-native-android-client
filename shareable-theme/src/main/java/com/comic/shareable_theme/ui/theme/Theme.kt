package com.comic.shareable_theme.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.comic.shareable_theme.ui.theme.constants.DarkColorScheme
import com.comic.shareable_theme.ui.theme.constants.LightColorScheme
import com.comic.shareable_theme.ui.theme.constants.Typography

@Composable
fun isDarkTheme(): Boolean {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        ThemeManager.initialize(context)
    }
    val isDarkTheme = ThemeManager.isDarkTheme.collectAsState()
    return isDarkTheme.value
}

@Composable
fun ShareableTheme(
    darkTheme: Boolean? = null,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val isDarkTheme = darkTheme ?: isDarkTheme()
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(
                    context
                )
            }

            isDarkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}




