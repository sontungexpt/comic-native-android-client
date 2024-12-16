package com.comic.android_native_client.ui.activities.index.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.exmaple.data.comics
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.Sliceable


enum class GENRE(
    @StringRes
    val title: Int,
    val mapId: String,
) {
    COLOR(
        title = R.string.colorful,
        mapId = ""
    ),
    COMEDY(
        title = R.string.comedy,
        mapId = ""
    ),
    ADVENTURE(
        title = R.string.adventure,
        mapId = ""
    ),
    REINCARNATION
        (
        title = R.string.reincarnation,
        mapId = ""
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    horizontalPadding: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    val carouselState = rememberCarouselState { 10 }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .wrapContentHeight()
            .padding(vertical = 24.dp)
            .padding(horizontal = horizontalPadding)
    ) {
        item(key = "_outstanding") {
            Text(
                text = stringResource(id = R.string.outstanding),
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HorizontalMultiBrowseCarousel(
                flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(state = carouselState),
                state = carouselState,
                preferredItemWidth = 280.dp,
                modifier = Modifier,
                itemSpacing = 12.dp,
            ) { index ->
                SimpleComic(
                    name = "Comic $index",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
                    enabled = true,
                    nameFontWeight = FontWeight.W500,
                    onclick = { },
                    modifier = Modifier
                        .maskClip(MaterialTheme.shapes.medium)
                        .width(300.dp)
                        .height(264.dp)
                )
            }


        }

        items(items = GENRE.entries, key = { it }) {
            Sliceable(
                loading = false,
                label = stringResource(it.title),
                labelStyle = MaterialTheme.typography.titleLarge,
                labelFontWeight = FontWeight.W600,
                items = comics
            ) {
                SimpleComic(
                    name = it.name,
                    imageUrl = it.imageUrl,
                    enabled = true,
                    nameFontWeight = FontWeight.W500,
                    onclick = {
                        navController.navigate(
                            route = Screen.ComicDetail(
                                id = it.id,
                                authors = it.authors,
                                imageUrl = it.imageUrl,
                                name = it.name,
                                description = it.description,
                            )
                        )
                    },
                    modifier = Modifier
                        .width(154.dp)
                        .height(248.dp)
                )
            }
        }

    }


}



