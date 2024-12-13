package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp

@Composable
fun LoadingIndicatorTextButton(
    loading: Boolean = false,
    loadingWrapperModifier: Modifier = Modifier,
    loadingColor: Color = ProgressIndicatorDefaults.circularColor,
    loadingStrokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    loadingTrackColor: Color = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    loadingstrokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
    loadingModifier: Modifier = Modifier,

    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    if (loading) {
        LoadingCircle(
            wrapperModifier = loadingWrapperModifier,
            color = loadingColor,
            strokeWidth = loadingStrokeWidth,
            trackColor = loadingTrackColor,
            strokeCap = loadingstrokeCap,
            modifier = loadingModifier,
        )
    } else {
        TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = colors,
            elevation = elevation,
            border = border,
            contentPadding = contentPadding,
            content = content
        )
    }
}


