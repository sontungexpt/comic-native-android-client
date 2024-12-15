package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comic.android_native_client.exmaple.data.genres
import com.comic.android_native_client.ui.activities.index.constants.GenreIconMap
import com.comic.android_native_client.ui.components.GenreCard
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = hiltViewModel<ExploreViewModel>(),
    horizontalPadding: Dp = 20.dp
) {

    val context = LocalContext.current

    HeaderScreen(
        contentPadding = horizontalPadding,
        headerText = "Explore"
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = genres,
                contentType = { it.javaClass },
                key = { it.id }
            ) { genre ->
                GenreIconMap[genre.name]?.let { icon ->
                    GenreCard(
                        genre = genre,
                        icon = icon,
                    )
                }
            }
        }
    }
}






