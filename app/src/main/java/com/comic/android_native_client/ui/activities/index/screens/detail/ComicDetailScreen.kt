package com.comic.android_native_client.ui.activities.index.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.ChapterCard
import com.comic.android_native_client.ui.components.common.ExpandableText

fun navigateToReadingScreen(
    navController: NavController,
    chapterInfo: Screen.ComicReading
) {
    navController.navigate(
        chapterInfo
    ) {
    }

}

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ComicDetailScreen(
    horizontalPadding: Dp = 16.dp,
    navController: NavController,
    currentComic: Screen.ComicDetail,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = horizontalPadding,
                vertical = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item(key = "image_and_name") {
            Header(
                comicName = currentComic.name,
                genres = listOf("Fantasy", "Manhwa", "Action", "Truyện Màu")
            )
        }
        stickyHeader {
            Surface(
                tonalElevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate(
                            Screen.ComicReading(
                                chapterId = "123",
                                id = "123",
                                chapterName = "Nhật Ký Từ Chức Cấp S"
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = stringResource(R.string.continue_reading),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            var favorited = remember { mutableStateOf(false) }
            ComicStatsSection(
                views = 100,
                rating = "200",
                favorited = favorited.value,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                onToggleFavorited = {
                    favorited.value = !favorited.value
                }
            )
        }

        item(key = "comic_information") {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.introduction)
            )
            Column {
                Text(
                    text = "Tác giả: Đang cập nhật",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        item(key = "comic_summary") {
            Text(
                text = stringResource(R.string.summary),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            ExpandableText(
                text = "Yoon Sdddd eora là một công chức thdkf df d df d fdf df dafdaf d fdf adf adfd fadf da fda fdfd adfd  sdf s dsf ds fds fds df s d fsfds d df d f ffdf df df dfsdf dfd fdfjdkfd jfdkf djfdk fdfjd kfdf jdf df ...",
                textAlign = TextAlign.Justify,
                collapsedMaxLines = 5
            )

        }


        item {
            SectionDivider()
            Text(
                text = "Danh sách tập",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

        }
        val chapters = listOf(
            "Nhật Ký Từ Chức Cấp S cua anh chang ngu dot",
            "Nhật Ký Từ Chức Cấp S"
        )
        items(
            items = chapters,
            key = { it },
            contentType = { it.javaClass }
        ) {
            ChapterCard(
                name = it,
                number = "1",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
                updateDate = "2021-09-01"
            )
        }
    }
}



