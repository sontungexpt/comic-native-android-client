package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.model.ComicChapter
import com.comic.android_native_client.data.model.NovelChapter
import com.comic.android_native_client.network.dto.response.ResourceInfo
import com.comic.android_native_client.ui.components.common.LoadingCircle

@Composable
fun ChapterContent(
    loading: Boolean,
    chapter: Chapter?,
    contentPadding: Dp = 14.dp,
    modifier: Modifier = Modifier
) {

    val chapterName = if (chapter != null) {
        if (chapter.name.isNotBlank())
            "Chapter ${chapter.num}: ${chapter.name}"
        else "Chapter ${chapter.num}"
    } else ""

    if (loading) {
        LoadingCircle(
            modifier = Modifier.size(30.dp),
            wrapperModifier = modifier.fillMaxWidth()
        )
    } else if (chapter == null) return
    else if (chapter is NovelChapter) {
        Text(
            text = chapterName,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = contentPadding
                )
        )
        Text(
            textAlign = TextAlign.Justify,
            text = chapter.content,
            modifier = Modifier.padding(horizontal = contentPadding)
        )
    } else if (chapter is ComicChapter) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                key = { it.number },
                contentType = { it.javaClass },
                items = chapter.imagePages,
            ) {
                AsyncImage(
                    model = if (chapter.resourceInfo is ResourceInfo.Relative)
                        chapter.resourceInfo.baseUrl + "/" + it.path
                    else it.path,
                    contentDescription = "Comic Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }

    }

}