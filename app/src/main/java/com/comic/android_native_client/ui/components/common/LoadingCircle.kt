package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp


@Composable
fun LoadingCircle(
    wrapperModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    loading: Boolean = true,
    propagateMinConstraints: Boolean = false,
    color: Color = ProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    trackColor: Color = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
) {
    Box(
        modifier = wrapperModifier,
        propagateMinConstraints = propagateMinConstraints,
        contentAlignment = Center
    ) {
        if (!loading) return@Box
        CircularProgressIndicator(
            color = color,
            strokeWidth = strokeWidth,
            trackColor = trackColor,
            strokeCap = strokeCap,
            modifier = modifier
        )
    }
}
