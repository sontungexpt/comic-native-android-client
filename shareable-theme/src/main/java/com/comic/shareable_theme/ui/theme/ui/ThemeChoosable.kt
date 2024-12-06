package com.comic.shareable_theme.ui.theme.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.comic.shareable_theme.ui.theme.ThemeManager
import kotlinx.coroutines.launch

@Composable
fun ThemeChoosable(
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (darkMode: Boolean, active: Boolean) -> Unit
) {
    val context = LocalContext.current
    val currState = ThemeManager.isDarkTheme.collectAsState().value
    val scope = rememberCoroutineScope()

    listOf(true, false).forEach { state ->
        Box(
            modifier = modifier.clickable {
                scope.launch {
                    if (state != currState) {
                        ThemeManager.saveDarkMode(context, state)
                        onThemeChange(state)
                    }
                }
            }
        ) {
            content(state, state == currState)
        }
    }
}