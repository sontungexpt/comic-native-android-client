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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.shareable_theme.ui.theme.ui.RowThemeChoosable

@Composable
fun ProfileScreen(
    navController: NavController,
    horizontalPadding: Dp = 18.dp,
) {
    val paddingX = 16
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ProfileHeader(
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
                    style = MaterialTheme.typography.headlineSmall
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




