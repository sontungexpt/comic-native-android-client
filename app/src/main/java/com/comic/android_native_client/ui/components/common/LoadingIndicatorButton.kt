package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun LoadingIndicatorButton(
    loading: Boolean,
    onClick: () -> Unit,

    loadingWrapperModifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    loadingPropagateMinConstraints: Boolean = false,
    loadingColor: Color = ProgressIndicatorDefaults.circularColor,
    loadingStrokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    loadingTrackColor: Color = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    loadingStrokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,

    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,


    content: @Composable RowScope.() -> Unit
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource

    ) {
        if (loading) {
            LoadingCircle(
                wrapperModifier = loadingWrapperModifier,
                modifier = loadingModifier,
                strokeWidth = loadingStrokeWidth,
                strokeCap = loadingStrokeCap,
                color = loadingColor,
                propagateMinConstraints = loadingPropagateMinConstraints,
                trackColor = loadingTrackColor,
            )
        } else {
            content()
        }

    }


}


@Preview
@Composable
fun IndeterminateCircularIndicatorPreview() {
    LoadingIndicatorButton(
        loading = true,
        onClick = {
        }
    ) {
        Text("test")
    }
}
