package com.comic.android_native_client.ui.activities.index.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.ui.components.SimpleComic
import com.comic.android_native_client.ui.components.common.TextWithIcon

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
        id = "6",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "7",
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

    Column(
        modifier = Modifier
            .padding(horizontal = 22.dp)
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
                items = list,
                key = { it.id }
            )
            {
                SimpleComic(
                    comic = it,
                    enabled = true,
                    belowName = {
                        TextWithIcon(
                            prefixIcon = Icons.Default.CalendarToday,
                            prefixIconTint = MaterialTheme.colorScheme.primary,
                            text = "1 day ago",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier.height(270.dp)

                )
            }

            item {
                Spacer(modifier = Modifier.height(0.dp))
            }

            // Adding CircularProgressIndicator after all items
            item(
                key = "loading",
                span = { GridItemSpan(maxCurrentLineSpan) })
            {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp) // Adjust size as needed
                    )
                }

            }
        }


    }
}