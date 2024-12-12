package com.comic.android_native_client.constants


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.android_native_client.R
import kotlinx.serialization.Serializable

sealed class Screen(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    // Tab Screens
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object Explore : Screen("explore", R.string.explore, Icons.Filled.Explore)
    object Favorite : Screen("favorite", R.string.favorite, Icons.Filled.Favorite)
    object Search : Screen("search", R.string.search, Icons.Filled.Home)

    object ProfileGraph : Screen("profile_graph", R.string.profile, Icons.Filled.Person) {
        object Profile : Screen("profile", R.string.profile, Icons.Filled.Person)
        object PrivacyPolicy :
            Screen("privacy_policy", R.string.privacy_policy, Icons.Filled.Policy)

        object AboutUs : Screen("about_us", R.string.about_us, Icons.Filled.Info)
        object Terms : Screen("terms_and_conditions", R.string.terms, Icons.Filled.Description)

    }


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
