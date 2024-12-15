package com.comic.android_native_client.ui.activities.splash

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.activities.index.AppActivity
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    delayTimeMs: Long = 5000
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(delayTimeMs) // 7 seconds
        context.startActivity(Intent(context, AppActivity::class.java))
    }


    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .fillMaxSize()
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Comic Hub Wallpaper",
            modifier = Modifier
                .size(200.dp)
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
                .clip(CircleShape)
        )
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}