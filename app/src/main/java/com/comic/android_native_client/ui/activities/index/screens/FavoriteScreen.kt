package com.comic.android_native_client.ui.activities.index.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.exmaple.data.comics
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon


@Composable
fun FavoriteScreen(
    horizontalPadding: Dp = 20.dp
) {

    Column(
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .wrapContentHeight()
    ) {
        Text(
            text = "Favorite",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {

            items(
                items = comics,
                contentType = { it.javaClass },
                key = { it.id }
            )
            {
                SimpleComic(
                    comic = it,
                    enabled = true,
                    footer = {
                        TextWithIcon(
                            prefixIcon = Icons.Default.CalendarToday,
                            prefixIconTint = MaterialTheme.colorScheme.primary,
                            text = "1 day ago",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                    modifier = Modifier.height(284.dp)

                )
            }


            item(
                key = "Loading Indicator",
                span = { GridItemSpan(maxLineSpan) })
            {
                LoadingCircle(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }


    }
}