package com.comic.android_native_client.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertComment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CommentButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    tint: Color = MaterialTheme.colorScheme.primary,
    iconModifier: Modifier = Modifier

) {
    IconButton(
        colors = colors,
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = Icons.AutoMirrored.Filled.InsertComment,
            contentDescription = "Add a comment",
            tint = tint,
        )
    }
}