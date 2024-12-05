package com.comic.android_native_client.ui.activities

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R

@Composable
fun SplashScreen() {
    val transition = rememberInfiniteTransition()

    val scale by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Logo of the app
            contentDescription = "Logo",
            modifier = Modifier
                .size(110.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                )
                .alpha(alpha) // Fade in effect
                .graphicsLayer(
                    shadowElevation = 8.dp.value,
                    shape = androidx.compose.foundation.shape.CircleShape,
                    clip = true
                )

        )

        // App name with fade-in effect after the logo
        Text(
            text = "Comic Dream", // Replace with your app's name
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                )
                .alpha(alpha) // Fade in effect
                .padding(top = 160.dp) // Adjust positioning of the app name
        )


    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}