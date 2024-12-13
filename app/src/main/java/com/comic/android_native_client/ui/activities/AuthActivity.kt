package com.comic.android_native_client.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.comic.android_native_client.ui.activities.authentications.AuthApp
import com.comic.shareable_theme.ui.theme.ShareableTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "AuthActivity"

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()

        setContent {
            ShareableTheme {
                AuthApp()
            }
        }
    }
}