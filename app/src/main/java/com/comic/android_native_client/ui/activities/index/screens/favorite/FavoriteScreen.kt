package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.android_native_client.ui.components.layout.HeaderScreen
import com.comic.android_native_client.ui.utils.formatTimeAgo


@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    horizontalPadding: Dp = 20.dp
) {
    val context = LocalContext.current
    val lazyGridState = rememberLazyGridState()

    HeaderScreen(
        contentPadding = horizontalPadding,
        modifier = Modifier.fillMaxSize(),
        headerText = "Favorite"

    ) {
        LazyVerticalGrid(
            state = lazyGridState,
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            items(
                items = favoriteViewModel.comics,
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
                                sourceName = it.originalSource.name,
                                genres = it.categories.map { it.name }
                            )
                        )
                    },
                    enabled = true,
                    footer = {
                        TextWithIcon(
                            prefixIcon = Icons.Default.Update,
                            prefixIconTint = MaterialTheme.colorScheme.primary,
                            text = formatTimeAgo(
                                context = context,
                                dateAgo = it.newChapterUpdatedAt
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.height(284.dp)

                )
            }
            if (favoriteViewModel.hasNextPage()) {
                item(
                    key = "Loading",
                    contentType = "loading",
                    span = { GridItemSpan(currentLineSpan = maxLineSpan) }
                ) {
                    LaunchedEffect(true) {
                        if (!favoriteViewModel.loadingMore) {
                            favoriteViewModel.loadMoreComics()
                        }
                    }
                    LoadingCircle(
                        loading = favoriteViewModel.loadingMore,
                        modifier = Modifier.size(28.dp),
                        wrapperModifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }

    }
}