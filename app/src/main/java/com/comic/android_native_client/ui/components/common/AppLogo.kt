package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.utils.navigateToApp

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.logo),
        alignment = alignment,
        contentDescription = contentDescription,
        modifier = modifier.clickable {
            if (onClick == null) navigateToApp(context)
            else onClick()
        },
        alpha = alpha,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}