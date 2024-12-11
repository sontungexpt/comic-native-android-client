package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.exmaple.data.genres
import com.comic.android_native_client.ui.activities.index.constants.GenreIconProvider
import com.comic.android_native_client.ui.components.GenreCard

@Composable
fun ExploreScreen(
    horizontalPadding: Dp = 20.dp
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
    ) {
        Text(
            text = "Explore",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = genres,
                contentType = { it.javaClass },
                key = { it.id }
            ) { genre ->
                val icon = GenreIconProvider.provideGenreIconMap(context)[genre.name]
                if (icon != null) {
                    GenreCard(
                        genre = genre,
                        icon = icon,
                    )
                }
            }
        }
    }
}






