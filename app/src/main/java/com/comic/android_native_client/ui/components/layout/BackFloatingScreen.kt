package com.comic.android_native_client.ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.comic.android_native_client.ui.components.common.BackIconButton

@Composable
fun BackFloatingScreen(
    onBackCLick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize(),
    backFloat: (@Composable BoxScope.() -> Unit)? = null,
    backButtonModifier: Modifier = Modifier
        .offset(x = 12.dp, y = 18.dp),
    backIconButtonModifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        content()

        if (backFloat != null) {
            backFloat()
        } else {
            BackIconButton(
                iconModifier = backIconButtonModifier,
                onBackClick = onBackCLick,
                modifier = backButtonModifier
                    .align(Alignment.TopStart)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .zIndex(1000f),
            )
        }


    }
}

