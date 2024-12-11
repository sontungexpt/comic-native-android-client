package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.CommentButton

val items = listOf(
    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComicReadingScreen(
    horizontalPadding: Dp = 20.dp,
    currentChapter: Screen.ComicReading,
) {
    Box(
        modifier = Modifier
    ) {

        LazyColumn(
            modifier = Modifier.padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            stickyHeader {
                Header(
                    chapterName = currentChapter.chapterName,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(64.dp)
                        .fillMaxWidth()
                        .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceDim,
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
            items(
                items = items
            ) {
                AsyncImage(
                    model = it,
                    contentDescription = "Comic Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }

        CommentButton(
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceBright,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .size(60.dp)
                .offset(-14.dp, -14.dp)
                .align(Alignment.BottomEnd),
            onClick = {

            })
    }
}

