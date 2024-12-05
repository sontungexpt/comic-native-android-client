package com.comic.android_native_client.ui.activities.index.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.ui.components.SimpleComic

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

    ),
    Comic(
        id = "4",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "5",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "5",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "5",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    )
)

@Composable
fun FavoriteScreen() {
    LazyVerticalGrid(
        modifier = Modifier.padding(22.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        items(list) {
            SimpleComic(
                comic = it,
                enabled = true,
                modifier = Modifier.height(250.dp)
            )
        }
    }
}