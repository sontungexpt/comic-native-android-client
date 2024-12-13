package com.comic.android_native_client.ui.activities.authentications

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
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.authentications.screens.login.LoginScreen
import com.comic.android_native_client.ui.activities.authentications.screens.signup.SignUpScreen

@Composable
fun AuthApp(
    horizontalPadding: Dp = 16.dp
) {
    val navController: NavHostController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Login.route
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding
                )
            }
            composable(route = Screen.Register.route) {
                SignUpScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding,
                )
            }
        }
    }
}

