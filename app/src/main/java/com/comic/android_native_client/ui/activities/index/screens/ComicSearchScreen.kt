package com.comic.android_native_client.ui.activities.index.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import com.comic.android_native_client.ui.activities.layout.HeaderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicSearchScreen(
) {
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
