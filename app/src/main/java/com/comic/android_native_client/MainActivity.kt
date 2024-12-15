package com.comic.android_native_client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.comic.android_native_client.network.services.UserService
import com.comic.android_native_client.ui.activities.splash.SplashScreen
import com.comic.android_native_client.ui.globalState.SharedUserState
import com.comic.android_native_client.ui.globalState.UserState
import com.comic.shareable_theme.ui.theme.ShareableTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var sharedUserState: SharedUserState


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        getCurrentUserIfLoggedIn()

        enableEdgeToEdge()

        setContent {
            ShareableTheme {
                SplashScreen(
                    delayTimeMs = 5000
                )
            }
        }
    }


    private fun getCurrentUserIfLoggedIn() {
        lifecycleScope.launch {
            try {
                val response = userService.fetchUserInfo()
                if (response.isSuccessful) {
                    response.body()?.let {
                        sharedUserState.setUser(
                            UserState(
                                name = it.name,
                                avatar = it.avatar
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}


