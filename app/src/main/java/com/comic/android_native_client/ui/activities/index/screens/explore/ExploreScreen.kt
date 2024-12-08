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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.exmaple.data.genres
import com.comic.android_native_client.ui.activities.index.constants.GenreIconMap

@Composable
fun ExploreScreen(
    horizontalPadding: Dp = 20.dp
) {

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
                GenreIconMap[genre.name]?.let {
                    GenreCard(
                        genre = genre,
                        icon = it,
                    )
                }
            }
        }
    }


}




