package com.comic.android_native_client.ui.activities.index.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComicDetailHeader(
    comicName: String,
    comicImageUrl: String,
    onGenreClick: (name: String, index: Int) -> Unit,
    comicGenres: List<String>,
) {
    Box(
        modifier = Modifier.height(570.dp)
    ) {
        AsyncImage(
            model = comicImageUrl,
            contentDescription = "Comic Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium)
        )

        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.62f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = comicName,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,

                    )
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for (i in comicGenres.indices) {
                        GenreTag(
                            onClick = {
                                onGenreClick(comicGenres[i], i)
                            },
                            modifier = Modifier,
                            name = comicGenres[i],
                        )
                    }

                }
            }
        }
    }


}