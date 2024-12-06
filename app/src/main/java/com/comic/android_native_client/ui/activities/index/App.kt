package com.comic.android_native_client.ui.activities.index

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.screens.ComicDetailScreen
import com.comic.android_native_client.ui.activities.index.screens.ComicReading
import com.comic.android_native_client.ui.activities.index.screens.ComicSearchScreen
import com.comic.android_native_client.ui.activities.index.screens.ExploreScreen
import com.comic.android_native_client.ui.activities.index.screens.FavoriteScreen
import com.comic.android_native_client.ui.activities.index.screens.home.HomeScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.ProfileScreen
import com.comic.shareable_theme.ui.theme.ShareableTheme


class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App(
) {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.HOME.route
        ) {
            composable(route = Screen.HOME.route) {
                HomeScreen()
            }
            composable(route = Screen.EXPLORE.route) {
                ExploreScreen()
            }
            composable(route = Screen.FAVORITE.route) {
                FavoriteScreen()
            }
            composable(route = Screen.PROFILE.route) {
                ProfileScreen()
            }

            // hidden screen
            composable(route = Screen.COMIC_DETAIL.route) {
                ComicDetailScreen()
            }
            composable(route = Screen.COMIC_READING.route) {
                ComicReading()
            }
            composable(route = Screen.SEARCH.route) {
                ComicSearchScreen()
            }
        }
    }
}


@Preview
@Composable
fun AppPreview() {
    ShareableTheme(
        dynamicColor = false
    ) {
        App()
    }
}