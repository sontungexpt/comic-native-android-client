package com.comic.android_native_client.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.ui.theme.AppTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComicImageCard(
    comic: Comic,
    enabled: Boolean,
    onclick: () -> Unit,
    modifier: Modifier
) {
    Card(
        enabled = enabled,
        onClick = onclick,
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = comic.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image of ${comic.name}",
                    modifier = Modifier
                        .fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Surface(
                        color = Color.Gray.copy(alpha = 0.35f),
                        modifier = Modifier
                            .defaultMinSize(minHeight = 100.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = comic.name,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .align(Alignment.Center)
                        )
                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun ComicImageCardPreview() {
    AppTheme {
        ComicImageCard(
            comic = Comic(
                authors = listOf("Author"),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
                name = "Ai day han nhu vay tu tien",
                description = "Comic description",
                rating = 5u,
                newChapters = listOf()

            ),

            enabled = true,
            onclick = {},
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(190.dp)
                .height(300.dp)
        )
    }

}


