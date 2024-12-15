package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.android_native_client.ui.components.layout.UnauthenticatedScreen
import com.comic.android_native_client.ui.utils.navigateToAuth
import com.comic.shareable_theme.ui.theme.ui.RowThemeChoosable

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>(),
    navController: NavController,
    horizontalPadding: Dp = 18.dp,
) {
    val context = LocalContext.current
    if (!profileViewModel.isLoggedIn) {
        UnauthenticatedScreen(
            onLoginClick = {
                navigateToAuth(context)
            },
            onHomeClick = {
                navController.navigate(Screen.Home)
            }
        )
        return
    }

    val userState by profileViewModel.userState.collectAsState()
    val paddingX = 16
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ProfileHeader(
                username = userState.name,
                paddingX = paddingX,
                imageHeight = 300,
            )
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = paddingX.dp)

            ) {
                TextWithIcon(
                    prefixIcon = Icons.Filled.FormatColorFill,
                    text = "Theme",
                    prefixIconTint = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )

                RowThemeChoosable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                )

            }


        }
        item {
            PremiumBanner(
                paddingX = paddingX,
                height = 200
            )

        }

        item {
            InfoList(navController = navController)
        }
    }
}




