package com.comic.android_native_client.constants


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.android_native_client.R
import kotlinx.serialization.Serializable

sealed class Screen(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    // Tab Screens
    object Home : Screen("home", R.string.home_screen_label, Icons.Filled.Home)
    object Explore : Screen("explore", R.string.explore_screen_label, Icons.Filled.Explore)
    object Favorite : Screen("favorite", R.string.favorite_screen_label, Icons.Filled.Favorite)
    object Profile : Screen("profile", R.string.profile_screen_label, Icons.Filled.Person)
    object Search : Screen("search", R.string.search, Icons.Filled.Home)

    @Serializable
    data class ComicDetail(
        val id: String,
        val authors: List<String>,
        val imageUrl: String,
        val name: String,
        val description: String,
    )

    @Serializable
    data class ComicReading(
        val comicId: String,
        val chapterId: String,
        val chapterName: String,
    )
}
