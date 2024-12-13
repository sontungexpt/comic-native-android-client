package com.comic.shareable_theme.ui.theme.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.comic.shareable_theme.ui.theme.ThemeManager
import com.comic.shareable_theme.ui.theme.datastore.ThemeDataStore
import kotlinx.coroutines.launch

@Composable
fun ThemeSwitcher(
    onToggleTheme: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val context = LocalContext.current
    val isDarkTheme = ThemeManager.isDarkTheme.collectAsState().value
    val scope = rememberCoroutineScope()

    Row {
        Switch(
            checked = isDarkTheme,
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource,
            thumbContent = thumbContent,
            onCheckedChange = { isDark ->
                scope.launch {
                    ThemeDataStore.setDarkMode(context, !isDark)
                    onToggleTheme?.invoke(!isDark)
                }
            }
        )
    }
}