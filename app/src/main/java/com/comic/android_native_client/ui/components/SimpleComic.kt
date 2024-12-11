package com.comic.android_native_client.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.comic.android_native_client.data.model.Comic
import com.comic.shareable_theme.ui.theme.ShareableTheme
import java.util.UUID


@Composable
fun SimpleComic(
    comic: Comic,
    onclick: () -> Unit = {},
    modifier: Modifier,
    enabled: Boolean = true,
    shape: CornerBasedShape = MaterialTheme.shapes.large,
    maxNameLines: Int = 1,
    nameFontSize: TextUnit = TextUnit.Unspecified,
    nameStyle: TextStyle = MaterialTheme.typography.titleMedium,
    nameFontFamily: FontFamily? = null,
    nameFontWeight: FontWeight? = null,
    nameFontStyle: FontStyle? = null,

    imageModifier: Modifier = Modifier,
    nameModifier: Modifier = Modifier,

    footer: @Composable () -> Unit = {},

    ) {
    Column(
        modifier = modifier
            .clip(shape)
            .clickable {
                if (enabled) {
                    onclick()
                }
            },
    ) {
        AsyncImage(
            model = comic.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = imageModifier
                .fillMaxSize()
                .clip(shape)
                .weight(1f),
        )
        Text(
            text = comic.name,
            maxLines = maxNameLines,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = nameStyle,
            fontSize = nameFontSize,
            fontWeight = nameFontWeight,
            fontFamily = nameFontFamily,
            fontStyle = nameFontStyle,
            modifier = nameModifier.fillMaxWidth()
        )

        footer()
    }

}

@Preview
@Composable
fun SimpleComicPreview() {
    ShareableTheme {
        Scaffold { innerPadding ->
            SimpleComic(
                comic = Comic(
                    id = UUID.randomUUID().toString(),
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
                    .width(170.dp)
                    .height(260.dp)
            )
        }

    }

}


