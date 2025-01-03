package com.comic.android_native_client.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.comic.android_native_client.R
import com.comic.shareable_theme.ui.theme.ShareableTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComicCard(
    imageUrl: String,
    name: String,
    authors: List<String>,
    newChapters: List<String>,
    onClick: () -> Unit = {},
    noChapter: Boolean = false,
    elevation: CardElevation = CardDefaults.cardElevation(4.dp),
    modifier: Modifier = Modifier,
    colors: CardColors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface
    )
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = elevation,
        colors = colors
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {

            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Image of ${name}",
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(0.23f)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = name,
                        maxLines = 2,
                        textAlign = TextAlign.Left,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = if (authors.isNotEmpty()) "By ${authors.joinToString(", ")}"
                        else "Authors: ${stringResource(R.string.on_updating)}",
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp,
                    )

                }
                if (!noChapter) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        maxItemsInEachRow = 3,
                        maxLines = 1,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val newChaptersSize = newChapters.size
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
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ComicCardPreview() {
    ShareableTheme {
        ComicCard(
            name = "Nam chinh muon ly hon nhung vo anh khong chiu",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
            authors = listOf("Author"),
            newChapters = listOf(),
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