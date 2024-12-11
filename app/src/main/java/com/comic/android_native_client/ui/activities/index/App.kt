package com.comic.android_native_client.ui.activities.index

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.screens.ComicSearchScreen
import com.comic.android_native_client.ui.activities.index.screens.FavoriteScreen
import com.comic.android_native_client.ui.activities.index.screens.detail.ComicDetailScreen
import com.comic.android_native_client.ui.activities.index.screens.explore.ExploreScreen
import com.comic.android_native_client.ui.activities.index.screens.home.HomeScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.ProfileScreen
import com.comic.android_native_client.ui.activities.index.screens.reading.ComicReadingScreen
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
    horizontalPadding: Dp = 18.dp
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
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding
                )
            }
            composable(route = Screen.Explore.route) {
                ExploreScreen(horizontalPadding = horizontalPadding)
            }
            composable(route = Screen.Favorite.route) {
                FavoriteScreen(horizontalPadding = horizontalPadding)
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen()
            }

            // hidden screen
            composable<Screen.ComicDetail> {
                val currentComic = it.toRoute<Screen.ComicDetail>()
                ComicDetailScreen(
                    horizontalPadding = horizontalPadding,
                    currentComic = currentComic,
                    navController = navController
                )
            }
            composable<Screen.ComicReading> {
                val currentChapterInfo = it.toRoute<Screen.ComicReading>()
                ComicReadingScreen(
                    horizontalPadding = horizontalPadding,
                    currentChapter = currentChapterInfo
                )
            }
            composable(route = Screen.Search.route) {
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