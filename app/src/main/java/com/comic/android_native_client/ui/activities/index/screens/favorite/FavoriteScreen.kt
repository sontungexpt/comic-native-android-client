package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.screens.profile.index.ProfileViewModel
import com.comic.android_native_client.ui.components.FavoriteIconToggleable
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.android_native_client.ui.components.layout.AuthenticatedRequiredScreen
import com.comic.android_native_client.ui.components.layout.HeaderScreen
import com.comic.android_native_client.ui.utils.formatTimeAgo
import com.comic.android_native_client.ui.utils.navigateToAuth


@Composable
fun FavoriteScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    horizontalPadding: Dp = 20.dp
) {
    val context = LocalContext.current
    AuthenticatedRequiredScreen(
        title = stringResource(R.string.favorite_comic),
        message = stringResource(R.string.favorite_screen_login_ask),
        loggedIn = { profileViewModel.isLoggedIn },
        onNavigateToAuth = {
            navigateToAuth(context)
        },
        onNavigateToHome = {
            navController.navigate(Screen.Home)
        }
    ) {
        val lazyGridState = rememberLazyGridState()
        HeaderScreen(
            contentPadding = horizontalPadding,
            modifier = Modifier.fillMaxSize(),
            headerText = stringResource(R.string.favorite)

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
                    Box(
                        modifier = Modifier.height(284.dp)
                    ) {
                        var dialogOpened by remember { mutableStateOf(false) }
                        var unFavoriteProcessing by remember { mutableStateOf(false) }

                        if (dialogOpened) {
                            UnfavoriteComicDiaglog(
                                loading = unFavoriteProcessing,
                                onDismiss = { dialogOpened = false },
                                onAgreement = {
                                    unFavoriteProcessing = true
                                    favoriteViewModel.unfavoriteComic(it, {
                                        unFavoriteProcessing = false
                                        dialogOpened = false
                                    })
                                }
                            )
                        }
                        FavoriteIconToggleable(
                            enabled = true,
                            favorited = true,
                            onToggleFavorited = {
                                dialogOpened = true
                            },
                            favoritedColor = MaterialTheme.colorScheme.error,
                            iconSize = 28.dp,
                            iconModifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.surface.copy(
                                        alpha = 0.6f
                                    ),
                                    shape = CircleShape
                                )
                                .padding(6.dp),
                            modifier = Modifier
                                .zIndex(10f)
                                .padding(4.dp)
                                .align(Alignment.TopEnd)
                        )
                        SimpleComic(
                            modifier = Modifier.fillMaxSize(),
                            name = it.name,
                            imageUrl = it.thumbnailUrl,
                            onclick = {
                                navController.navigate(
                                    Screen.ComicDetail(
                                        id = it.id,
                                        imageUrl = it.thumbnailUrl,
                                        name = it.name,
                                        sourceName = it.originalSource.name,
                                        genres = it.categories.map {
                                            it.name
                                        }
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
                            })
                    }

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


}