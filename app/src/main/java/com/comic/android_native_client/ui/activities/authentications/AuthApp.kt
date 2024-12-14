package com.comic.android_native_client.ui.activities.authentications

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comic.android_native_client.constants.IScreen
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.authentications.screens.login.LoginScreen
import com.comic.android_native_client.ui.activities.authentications.screens.signup.SignUpScreen
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

@Composable
fun AuthApp(
    initialScreen: IScreen = Screen.Login,
    horizontalPadding: Dp = 16.dp
) {
    val navController: NavHostController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = initialScreen,

            ) {
            composable<Screen.Login> {
                LoginScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding
                )
            }
            composable<Screen.Register> {
                SignUpScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding,
                )
            }
        }
    }
}

