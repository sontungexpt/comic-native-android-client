package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.android_native_client.ui.components.layout.HeaderScreen


@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>(),
    horizontalPadding: Dp = 20.dp,

    ) {
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (!favoriteViewModel.loadingMore) {
                    val totalItems = lazyGridState.layoutInfo.totalItemsCount
                    val lastVisibleItem = visibleItems.lastOrNull()?.index
                    if (
                        lastVisibleItem == null
                        || lastVisibleItem >= totalItems - 1
                    ) {
                        favoriteViewModel.loadMoreComics()
                    }
                }
            }
    }
    HeaderScreen(
        contentPadding = horizontalPadding,
        headerText = "Favorite"

    ) {
        LazyVerticalGrid(
            state = lazyGridState,
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {

            items(
                items = favoriteViewModel.favoriteComics,
                contentType = { it.javaClass },
                key = { it.id }
            ) {
                SimpleComic(
                    name = it.name,
                    imageUrl = it.thumbnailUrl,
                    onclick = {
                        navController.navigate(
                            Screen.ComicDetail(
                                id = it.id,
                                imageUrl = it.thumbnailUrl,
                                name = it.name,
                                genres = it.categories.map { it.name }
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
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.height(284.dp)

                )
            }

        }
        if (favoriteViewModel.loadingMore) {
            LoadingCircle(
                wrapperModifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}