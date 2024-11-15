package com.comic.android_native_client.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.comic.android_native_client.data.model.Comic
import com.comic.shareable_theme.ui.theme.ShareableTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComicCard(
    comic: Comic,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(0.26f)
            ) {
                AsyncImage(
                    model = comic.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image of ${comic.name}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.65f))
                ) {
                    Text(
                        text = "${comic.rating} ⭐",
                        modifier = Modifier
                            .padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 4.dp),
                        fontSize = 12.sp,
                    )

                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp)
                    .fillMaxHeight(0.9f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = comic.name,
                        maxLines = 2,
                        textAlign = TextAlign.Justify,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "By ${comic.authors.joinToString(", ")}",
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp,
                    )

                }
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 3,
                    maxLines = 1,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val newChaptersSize = comic.newChapters.size
                    val loop_times = if (newChaptersSize > 3) {
                        3
                    } else {
                        newChaptersSize
                    }
                    if (loop_times == 0) {
                        Text(
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            text = "No chapter available"
                        )
                        return@FlowRow
                    }
                    for (i in 1..loop_times) {
                        Button(
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .height(32.dp)
                                .align(Alignment.CenterVertically)
                                .fillMaxWidth(0.31f),
                            onClick = {
                                println("Button $i clicked")
                            }) {
                            Text(
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                text = "10000"
                            )
                        }
                    }
                }

            }

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ComicCardPreview() {
    ShareableTheme {
        ComicCard(
            comic = Comic(
                authors = listOf("Author"),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
                name = "Nam chinh muon ly hon nhung vo anh khong chiu",
                description = "Comic description",
                rating = 5u,
                newChapters = listOf()

            ),
            modifier = Modifier
                .combinedClickable(
                    enabled = true,
                    onClick = { println("click card") },
                    onLongClick = { println("long click card") }
                )
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp)
        )
    }

}