package com.comic.android_native_client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.comic.android_native_client.ui.activities.index.App
import com.comic.shareable_theme.ui.theme.ShareableTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        //startActivity(Intent(this, AuthActivity::class.java))

        setContent {
            ShareableTheme {
                App()
            }
        }
    }
}


