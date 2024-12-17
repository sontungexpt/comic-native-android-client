package com.comic.shareable_theme.ui.theme.ui


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.comic.shareable_theme.ui.theme.ThemeManager
import com.comic.shareable_theme.ui.theme.constants.DarkColorScheme
import com.comic.shareable_theme.ui.theme.constants.LightColorScheme
import kotlinx.coroutines.launch

@Composable
fun RowThemeChoosable(
    onThemeChange: ((state: Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    itemShape: Shape = MaterialTheme.shapes.medium,
    itemModifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val currState = ThemeManager.isDarkTheme.collectAsState().value
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = horizontalArrangement,
        modifier = modifier.fillMaxWidth(),
    ) {

        listOf(true, false).forEach { state ->
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state) DarkColorScheme.background else LightColorScheme.background,
                    contentColor = if (state) DarkColorScheme.onBackground else LightColorScheme.onBackground
                ),
                onClick = {
                    scope.launch {
                        if (state != currState) {
                            ThemeManager.saveDarkMode(context, state)
                            onThemeChange?.invoke(state)
                        }
                    }
                },
                shape = itemShape, // Apply rounded corners to button
                modifier = itemModifier
                    .fillMaxHeight()
                    .weight(1f)
                    .border(
                        width = 4.dp,
                        color = if (state) DarkColorScheme.outline else LightColorScheme.outline,
                        shape = itemShape // Ensure the border respects the rounded corners
                    )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if (state == currState) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.TopEnd),
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Theme Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = if (state) "Dark" else "Light",
                        color = if (state) DarkColorScheme.onBackground else LightColorScheme.onBackground
                    )
                }
            }
        }
    }
}