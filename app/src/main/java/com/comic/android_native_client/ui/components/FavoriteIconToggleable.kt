package com.comic.android_native_client.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIconToggleable(
    favorited: Boolean,
    onToggleFavorited: (favorited: Boolean) -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },

    modifier: Modifier = Modifier,

    iconSize: Dp = 24.dp,
    iconModifier: Modifier = Modifier,
    favoritedColor: Color = MaterialTheme.colorScheme.tertiary,
    unFavoritedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
    disabledColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
) {

    IconToggleButton(
        checked = favorited,
        onCheckedChange = onToggleFavorited,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Icon(
            imageVector = if (favorited) Icons.Filled.Favorite else Icons.Outlined.Favorite,
            contentDescription = null,
            tint = if (!enabled) disabledColor else if (favorited) favoritedColor else unFavoritedColor,
            modifier = iconModifier.size(iconSize),
        )
    }
}