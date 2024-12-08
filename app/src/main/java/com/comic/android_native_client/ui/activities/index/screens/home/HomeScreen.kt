package com.comic.android_native_client.ui.activities.index.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.Sliceable
import com.comic.shareable_theme.ui.theme.ShareableTheme

val list = listOf(
    Comic(
        id = "1",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "2",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "3",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "4",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    )
)

enum class GENRE(
    @StringRes
    val title: Int,
    val mapId: String,
) {
    COLOR(
        title = R.string.color_comic_title,
        mapId = ""
    ),
    COMEDY(
        title = R.string.comedy_comic_title,
        mapId = ""
    ),
    ADVENTURE(
        title = R.string.adventure_comic_title,
        mapId = ""
    ),
    REINCARNATION
        (
        title = R.string.reincarnation_comic_title,
        mapId = ""
    )

}

@Composable
fun HomeScreen(
    horizontalPadding: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .wrapContentHeight()
            .padding(24.dp)
            .padding(horizontal = horizontalPadding)
    ) {
        items(items = GENRE.values(), key = { it }) {
            Sliceable(
                loading = false,
                label = stringResource(it.title),
                labelFontSize = 24.sp,
                labelFontWeight = FontWeight.W600,
                items = list
            ) {
                SimpleComic(
                    comic = it,
                    enabled = true,
                    nameFontWeight = FontWeight.W500,
                    onclick = { },
                    modifier = Modifier
                        .width(148.dp)
                        .height(264.dp)
                )
            }
        }

    }


}

@Composable
@Preview
fun HomeScreenPreview() {
    ShareableTheme {
        Scaffold { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))

        }
    }
}

