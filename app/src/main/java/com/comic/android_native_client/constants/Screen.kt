package com.comic.android_native_client.constants

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.android_native_client.R

enum class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {

    HOME("home", R.string.home_screen_label, Icons.Filled.Home),
    COMIC_DETAIL("comic_detail", R.string.comic_detail_screen_label, Icons.Filled.Home),
    COMIC_READING("comic_reading", R.string.comic_reading_screen_label, Icons.Filled.Home),
    SEARCH("search", R.string.search_screen_label, Icons.Filled.Home),
    EXPLORE("explore", R.string.explore_screen_label, Icons.Filled.Explore),
    FAVORITE("favorite", R.string.favorite_screen_label, Icons.Filled.Favorite),
    PROFILE("profile", R.string.profile_screen_label, Icons.Filled.Person)

}