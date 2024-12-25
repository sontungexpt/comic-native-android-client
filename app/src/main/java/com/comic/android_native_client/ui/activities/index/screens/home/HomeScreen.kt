package com.comic.android_native_client.ui.activities.index.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.Sliceable

enum class GENRE(
    @StringRes
    val title: Int,
    val mapId: String,
) {
    COLOR(
        title = R.string.colorful,
        mapId = "6724cc2718ed6853571a86a6"
    ),
    COMEDY(
        title = R.string.manga,
        mapId = "6724cc2718ed6853571a8689"
    ),
    ADVENTURE(
        title = R.string.action,
        mapId = "6724cc2718ed6853571a8676"
    ),
    REINCARNATION(
        title = R.string.reincarnation,
        mapId = "6724cc2718ed6853571a8679"
    ),
    ROMANCE(
        title = R.string.romance,
        mapId = "6724cc2718ed6853571a8693"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    horizontalPadding: Dp = 16.dp,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {

    val OUTSTANDING_KEY = homeViewModel.OUTSTANDING_KEY

    LaunchedEffect(Unit) {
        homeViewModel.initComics(
            GENRE.entries.map { it.mapId }
        )
    }

    fun handleComicClick(comic: Comic) {
        navController.navigate(
            route = Screen.ComicDetail(
                id = comic.id,
                imageUrl = comic.thumbnailUrl,
                name = comic.name,
                sourceName = comic.originalSource.name,
                genres = comic.categories.map {
                    it.name
                }
            )
        )
    }

    val carouselState = rememberCarouselState { 10 }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .padding(horizontal = horizontalPadding)
    ) {
        item(
            contentType = OUTSTANDING_KEY,
            key = OUTSTANDING_KEY
        ) {
            Text(
                text = stringResource(id = R.string.outstanding),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (homeViewModel.comicsMap[OUTSTANDING_KEY]?.loading != false) {
                LoadingCircle(
                    wrapperModifier = Modifier
                        .height(264.dp)
                        .fillMaxWidth(),
                    modifier = Modifier
                        .padding(vertical = 28.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                HorizontalMultiBrowseCarousel(
                    flingBehavior = CarouselDefaults
                        .singleAdvanceFlingBehavior(state = carouselState),
                    state = carouselState,
                    preferredItemWidth = 310.dp,
                    modifier = Modifier,
                    itemSpacing = 12.dp,
                ) { index ->
                    val comics = homeViewModel.comicsMap[OUTSTANDING_KEY]?.comics
                    if (index < comics?.size ?: 0) {
                        comics?.get(index)?.let {
                            SimpleComic(
                                name = it.name,
                                imageUrl = it.thumbnailUrl,
                                enabled = true,
                                nameFontWeight = FontWeight.W500,
                                onclick = {
                                    handleComicClick(it)
                                },
                                modifier = Modifier
                                    .maskClip(MaterialTheme.shapes.small)
                                    .width(310.dp)
                                    .height(268.dp)
                            )
                        }

                    }
                }

            }


        }

        items(
            items = GENRE.entries,
            key = { it },
            contentType = { it.javaClass }
        ) {
            Sliceable(
                heightPrediction = 248.dp,
                modifier = Modifier.fillMaxWidth(),
                items = homeViewModel.comicsMap[it.mapId]?.comics ?: emptyList(),
                loading = homeViewModel.comicsMap[it.mapId]?.loading ?: true,
                header = {
                    val title = stringResource(it.title)
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.W600
                            ),
                        )
                        TextButton(
                            onClick = {
                                navController.navigate(
                                    Screen.ComicByCategory(
                                        id = it.mapId,
                                        name = title
                                    )
                                )
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.see_all),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            ) {
                SimpleComic(
                    name = it.name,
                    imageUrl = it.thumbnailUrl,
                    enabled = true,
                    nameFontWeight = FontWeight.W500,
                    onclick = {
                        handleComicClick(it)
                    },
                    modifier = Modifier
                        .width(154.dp)
                        .height(248.dp)
                )
            }
        }

    }


}


