package com.comic.android_native_client.ui.activities.index.screens.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicSearchScreen(navController: NavHostController, horizontalPadding: Dp) {
    HeaderScreen(
        header = {
            SearchBar(
                inputField = {

                },
                expanded = false,
                onExpandedChange = {},
            ) {

            }
        }
    ) {

    }

}
