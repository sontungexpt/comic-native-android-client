package com.comic.shareable_theme.ui.theme.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comic.shareable_theme.ui.theme.ThemeManager
import kotlinx.coroutines.launch


@Composable
fun ThemeToggleIcon(
    onToggleTheme: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    colors: IconToggleButtonColors = IconButtonDefaults.iconToggleButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },

    enabled: Boolean = true,
    iconSize: Int = 40,
    iconModifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    val context = LocalContext.current
    val isDarkTheme by ThemeManager.isDarkTheme.collectAsState()

    val rotation by animateFloatAsState(if (isDarkTheme) 180f else 0f)
    val scope = rememberCoroutineScope()

    IconToggleButton(
        checked = isDarkTheme,
        onCheckedChange = {
            scope.launch {
                ThemeManager.saveDarkMode(context, !isDarkTheme)
                onToggleTheme?.invoke(!isDarkTheme)
            }

        },
        modifier = modifier
            .graphicsLayer(rotationZ = rotation),
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource

    ) {
        Icon(
            imageVector = if (isDarkTheme) Icons.Filled.DarkMode else Icons.Filled.Brightness5,
            contentDescription = if (isDarkTheme) "Dark Mode" else "Light Mode",
            modifier = iconModifier.size(iconSize.dp),
            tint = tint
        )
    }

}
