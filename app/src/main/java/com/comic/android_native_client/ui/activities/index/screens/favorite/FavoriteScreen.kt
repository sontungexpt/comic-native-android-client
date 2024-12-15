package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.exmaple.data.comics
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.android_native_client.ui.components.layout.HeaderScreen


@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>(),
    horizontalPadding: Dp = 20.dp
) {

    HeaderScreen(
        contentPadding = horizontalPadding,
        headerText = "Favorite"
    ) {
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
                    onclick = {
                        navController.navigate(
                            Screen.ComicDetail(
                                id = it.id,
                                authors = it.authors,
                                imageUrl = it.imageUrl,
                                name = it.name,
                                description = it.description,
                            )
                        )
                    },
                    enabled = true,
                    footer = {
                        TextWithIcon(
                            prefixIcon = Icons.Default.Update,
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