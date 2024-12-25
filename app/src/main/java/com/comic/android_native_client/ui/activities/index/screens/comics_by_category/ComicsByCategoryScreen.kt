package com.comic.android_native_client.ui.activities.index.screens.comics_by_category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.ComicCard
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.BackFloatingScreen
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@Composable
fun ComicsByCategoryScreen(
    currentCategory: Screen.ComicByCategory,
    navController: NavController,
    horizontalPadding: Dp = 16.dp,
    comicByCategoryViewModel: ComicByCategoryViewModel = hiltViewModel<ComicByCategoryViewModel>()
) {
    LaunchedEffect(currentCategory.id) {
        comicByCategoryViewModel.resetState()
        comicByCategoryViewModel.fetchNextPage(currentCategory.id)
    }
    BackFloatingScreen(
        backButtonModifier = Modifier
            .offset(x = 12.dp, y = 14.dp),
        onBackCLick = { navController.popBackStack() }
    ) {
        HeaderScreen(
            header = {
                Text(
                    text = currentCategory.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 16.dp
                        )
                        .padding(start = 70.dp, end = horizontalPadding),
                )
            },
            contentPadding = horizontalPadding,
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = comicByCategoryViewModel.comics,
                    key = { it.id },
                    contentType = { it.javaClass }
                ) {
                    ComicCard(
                        colors = CardDefaults.cardColors(
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        newChapters = it.newChapters.map { it.chapter },
                        imageUrl = it.thumbnailUrl,
                        name = it.name,
                        authors = it.authors.map { it.name },
                        onClick = {
                            navController.navigate(
                                Screen.ComicDetail(
                                    id = it.id,
                                    name = it.name,
                                    imageUrl = it.thumbnailUrl,
                                    sourceName = it.originalSource.name,
                                    genres = it.categories.map {
                                        it.name
                                    },
                                )
                            )
                        }
                    )
                }

                item(key = "loading") {
                    if (!comicByCategoryViewModel.lastPageReached) {
                        LoadingCircle(
                            loading = comicByCategoryViewModel.loading
                        )
                        LaunchedEffect(Unit) {
                            comicByCategoryViewModel.fetchNextPage(currentCategory.id)
                        }
                    }
                }
            }
        }
    }
}
