package com.comic.android_native_client.ui.activities.index.screens


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.layout.CenterLayout

@Composable
fun ErrorScreen(
    error: Screen.Error,
    navigateToHome: () -> Unit,
    horizontalPadding: Dp
) {
    CenterLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error Icon",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = if (error.shortMessage.isNotBlank())
                "${error.code} - ${error.shortMessage}"
            else "${error.code}",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        if (!error.longMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = error.longMessage,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navigateToHome()

                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Go Back to Home")
            }

        }
    }
}
