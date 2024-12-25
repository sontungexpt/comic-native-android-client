package com.comic.android_native_client.constants


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.comic.android_native_client.R
import kotlinx.serialization.Serializable

interface IScreen {
    fun toRoute(): String? = this::class.qualifiedName
    fun icon(): ImageVector
}

interface StaticScreen : IScreen

interface DynamicScreen : IScreen

interface NavigationGraph : IScreen {
    val startDestination: IScreen
}

@Serializable
sealed class Screen(
    @StringRes
    val label: Int,
) : StaticScreen {

    @Serializable
    object Login : Screen(R.string.login) {
        override fun icon(): ImageVector = Icons.AutoMirrored.Filled.Login
    }

    @Serializable
    object Register :
        Screen(R.string.register) {
        override fun icon(): ImageVector = Icons.AutoMirrored.Filled.Login
    }

    // Tab Screens
    @Serializable
    object Home : Screen(R.string.home) {
        override fun icon(): ImageVector = Icons.Filled.Home
    }

    @Serializable
    object Explore : Screen(R.string.explore) {
        override fun icon(): ImageVector = Icons.Filled.Explore
    }

    @Serializable
    object Favorite : Screen(R.string.favorite) {
        override fun icon(): ImageVector = Icons.Filled.Favorite
    }

    @Serializable
    object Search : Screen(R.string.search) {
        override fun icon(): ImageVector = Icons.Filled.Search
    }

    @Serializable
    object NotFound : Screen(R.string.not_found) {
        override fun icon(): ImageVector = Icons.Filled.ErrorOutline
    }

    @Serializable
    object ProfileGraph :
        Screen(R.string.profile),
        NavigationGraph {

        @Serializable
        object Profile : Screen(R.string.profile) {
            override fun icon(): ImageVector = Icons.Filled.Person
        }

        @Serializable
        object PrivacyPolicy :
            Screen(R.string.privacy_policy) {
            override fun icon(): ImageVector = Icons.Filled.Person
        }

        @Serializable
        object EditProfile : Screen(R.string.edit_profile) {
            override fun icon(): ImageVector = Icons.Filled.Edit
        }

        @Serializable
        object AboutUs : Screen(R.string.about_us) {
            override fun icon(): ImageVector = Icons.Filled.Info
        }

        @Serializable
        object Terms :
            Screen(R.string.terms) {
            override fun icon(): ImageVector = Icons.Filled.Info
        }

        override val startDestination: IScreen
            get() = Profile

        override fun icon(): ImageVector {
            return Icons.Filled.Person
        }
    }

    @Serializable
    data class Error(
        val code: Int,
        val shortMessage: String,
        val longMessage: String
    ) : DynamicScreen {
        override fun icon(): ImageVector {
            return Icons.Filled.Error
        }
    }


    @Serializable
    data class ComicDetail(
        val id: String,
        val imageUrl: String,
        val name: String? = null,
        val sourceName: String,

        // first genre is id and second is name
        val genres: List<String>? = null,
    ) : DynamicScreen {
        override fun icon(): ImageVector {
            return Icons.Filled.Details
        }
    }

    @Serializable
    data class ComicByCategory(
        val id: String,
        val name: String,
    ) : DynamicScreen {
        override fun icon(): ImageVector {
            return Icons.Filled.Category
        }
    }

    @Serializable
    data class ComicReading(
        val comicId: String,
        val chapterId: String,
        val lastestRead: Boolean,
    ) : DynamicScreen {
        override fun icon(): ImageVector {
            return Icons.Filled.Book
        }
    }
}


