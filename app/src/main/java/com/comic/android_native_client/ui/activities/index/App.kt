package com.comic.android_native_client.ui.activities.index

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.comic.android_native_client.constants.DYNAMIC_THEME
import com.comic.android_native_client.constants.IScreen
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.screens.ErrorScreen
import com.comic.android_native_client.ui.activities.index.screens.NotFoundScreen
import com.comic.android_native_client.ui.activities.index.screens.comics_by_category.ComicsByCategoryScreen
import com.comic.android_native_client.ui.activities.index.screens.detail.ComicDetailScreen
import com.comic.android_native_client.ui.activities.index.screens.detail.ComicDetailViewModel
import com.comic.android_native_client.ui.activities.index.screens.explore.ExploreScreen
import com.comic.android_native_client.ui.activities.index.screens.explore.ExploreViewModel
import com.comic.android_native_client.ui.activities.index.screens.favorite.FavoriteScreen
import com.comic.android_native_client.ui.activities.index.screens.favorite.FavoriteViewModel
import com.comic.android_native_client.ui.activities.index.screens.home.HomeScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.index.ProfileScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.index.ProfileViewModel
import com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens.AboutUsScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens.EditProfileScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens.PrivacyPolicyScreen
import com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens.TermsScreen
import com.comic.android_native_client.ui.activities.index.screens.reading.ComicReadingScreen
import com.comic.android_native_client.ui.activities.index.screens.reading.CommentViewModel
import com.comic.android_native_client.ui.activities.index.screens.search.ComicSearchScreen
import com.comic.android_native_client.ui.activities.index.screens.search.ComicSearchViewModel
import com.comic.shareable_theme.ui.theme.ShareableTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "AppActivity"

@AndroidEntryPoint
class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContent {
            ShareableTheme(
                dynamicColor = DYNAMIC_THEME
            ) {
                App()
            }
        }
    }
}


@Composable
fun App(
    initialScreen: IScreen = Screen.Home,
    horizontalPadding: Dp = 18.dp
) {
    val navController: NavHostController = rememberNavController()

    // hóist the viewmodel
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
    val exploreViewModel = hiltViewModel<ExploreViewModel>()
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val searchViewModel = hiltViewModel<ComicSearchViewModel>()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = initialScreen
        ) {
            composable<Screen.Home> {
                HomeScreen(
                    navController = navController,
                    horizontalPadding = horizontalPadding
                )
            }
            composable<Screen.Explore> {
                ExploreScreen(
                    navController = navController,
                    exploreViewModel = exploreViewModel,
                    searchViewModel = searchViewModel,
                    horizontalPadding = horizontalPadding
                )
            }
            composable<Screen.Favorite> {
                FavoriteScreen(
                    favoriteViewModel = favoriteViewModel,
                    navController = navController,
                    profileViewModel = profileViewModel,
                    horizontalPadding = horizontalPadding,
                )
            }

            navigation<Screen.ProfileGraph>(
                startDestination = Screen.ProfileGraph.startDestination
            ) {
                composable<Screen.ProfileGraph.Profile> {
                    ProfileScreen(
                        profileViewModel = profileViewModel,
                        navController = navController,
                        horizontalPadding = horizontalPadding
                    )
                }
                composable<Screen.ProfileGraph.EditProfile> {
                    EditProfileScreen(
                        navController = navController,
                        horizontalPadding = horizontalPadding
                    )
                }

                composable<Screen.ProfileGraph.PrivacyPolicy> {
                    PrivacyPolicyScreen(
                        navController = navController,
                        horizontalPadding = horizontalPadding
                    )
                }
                composable<Screen.ProfileGraph.AboutUs> {
                    AboutUsScreen(
                        navController = navController,
                        horizontalPadding = horizontalPadding
                    )
                }

                composable<Screen.ProfileGraph.Terms> {
                    TermsScreen(
                        navController = navController,
                        horizontalPadding = horizontalPadding
                    )
                }
            }


            // hidden screen
            composable<Screen.ComicDetail> {
                val currentComic = it.toRoute<Screen.ComicDetail>()
                val comicDetailViewModel = hiltViewModel<ComicDetailViewModel>()
                ComicDetailScreen(
                    comicDetailViewModel = comicDetailViewModel,
                    horizontalPadding = horizontalPadding,
                    currentComic = currentComic,
                    navController = navController,
                    favoriteViewModel = favoriteViewModel,

                    )
            }
            composable<Screen.ComicReading> {
                val currentChapterInfo = it.toRoute<Screen.ComicReading>()
                val commentViewModel = hiltViewModel<CommentViewModel>()

                ComicReadingScreen(
                    commentViewModel = commentViewModel,
                    navController = navController,
                    horizontalPadding = horizontalPadding,
                    currentChapter = currentChapterInfo
                )
            }
            composable<Screen.Search> {
                ComicSearchScreen(
                    searchViewModel = searchViewModel,
                    navController = navController,
                    horizontalPadding = horizontalPadding,
                )
            }

            composable<Screen.ComicByCategory> {
                val currentCategory = it.toRoute<Screen.ComicByCategory>()
                ComicsByCategoryScreen(
                    currentCategory = currentCategory,
                    navController = navController,
                    horizontalPadding = horizontalPadding,
                )
            }

            composable<Screen.Error> {
                val error = it.toRoute<Screen.Error>()
                ErrorScreen(
                    error = error,
                    horizontalPadding = horizontalPadding,
                    navigateToHome = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Home) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<Screen.NotFound> {
                NotFoundScreen(
                    horizontalPadding = horizontalPadding,
                    navigateToHome = {
                        navController.navigate(Screen.Home) {
                            popUpTo(Screen.Home) {
                                inclusive = true
                            }
                        }
                    }
                )
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