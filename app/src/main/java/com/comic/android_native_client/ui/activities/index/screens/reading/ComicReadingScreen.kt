package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.data.model.ComicChapter
import com.comic.android_native_client.data.model.NovelChapter
import com.comic.android_native_client.network.dto.response.ResourceInfo
import com.comic.android_native_client.ui.components.CommentButtonWithModal
import com.comic.android_native_client.ui.components.CommentCard
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.BackFloatingScreen

val comments = listOf(
    Pair("Quang Sáng", "ném trong .env github nó tự xóa mà"),
    Pair("kietphamtan7777", "Bạn quăng thêm 100 cái api key nữa lừa hack"),
    Pair("aubur", "là sao vậy ạ?"),
    Pair("Quang Sáng", "ném trong .env github nó tự xóa mà"),
    Pair("Quang Sáng", "ném trong .env github nó tự xóa mà"),

    )

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun ComicReadingScreen(
    horizontalPadding: Dp = 20.dp,
    comicReadingViewModel: ComicReadingViewModel = hiltViewModel<ComicReadingViewModel>(),
    navController: NavController,
    currentChapter: Screen.ComicReading,
) {
    val uiState by comicReadingViewModel.uiState.collectAsState()

    val chapterName = if (uiState.chapter != null) {
        if (uiState.chapter!!.name.isNotBlank())
            "Chapter ${uiState.chapter!!.num}: ${uiState.chapter!!.name}"
        else "Chapter ${uiState.chapter!!.num}"
    } else ""


    fun handleNotFound() {
        navController.navigate(Screen.NotFound) {
            popUpTo(Screen.Home) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(
        currentChapter.chapterId,
        currentChapter.comicId
    ) {
        comicReadingViewModel.loadChapter(
            comicId = currentChapter.comicId,
            chapterId = currentChapter.chapterId,
            onNotFound = { handleNotFound() }
        )
        comicReadingViewModel.loadAllChapters(
            comicId = currentChapter.comicId,
            onNotFound = { handleNotFound() }
        )

    }


    BackFloatingScreen(
        onBackCLick = { navController.popBackStack() },
        modifier = Modifier.fillMaxSize()
    ) {
        ChapterNavigationBar(
            chapterName = chapterName,
            hasNext = uiState.hasNext,
            hasPrev = uiState.hasPrev,
            onClickNext = {
                comicReadingViewModel.nextChapter(
                    comicId = currentChapter.comicId,
                    onNotFound = { handleNotFound() }
                )
            },
            onClickPrev = {
                comicReadingViewModel.prevChapter(
                    comicId = currentChapter.comicId,
                    onNotFound = { handleNotFound() }
                )
            },
            onClickName = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(64.dp)
                .zIndex(100f)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colorScheme.surfaceDim,
                    shape = MaterialTheme.shapes.medium
                )
        )
        if (uiState.chapterLoading) {
            LoadingCircle(
                modifier = Modifier.size(30.dp),
                wrapperModifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)

            )
        } else if (uiState.chapter == null) {
            return@BackFloatingScreen
        } else if (uiState.chapter is NovelChapter) {
            val chapter = uiState.chapter as NovelChapter
            Text(
                text = chapterName,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
                    .padding(top = 10.dp)
            )

            Text(
                textAlign = TextAlign.Justify,
                text = chapter.content,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding)
                    .padding(top = 10.dp)
            )
        } else if (uiState.chapter is ComicChapter) {
            val comicChapter = uiState.chapter as ComicChapter
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    key = { it.number },
                    contentType = { it.javaClass },
                    items = comicChapter.imagePages,
                ) {
                    val imagePath = if (comicChapter.resourceInfo is ResourceInfo.Relative)
                        comicChapter.resourceInfo.baseUrl + "/" + it.path
                    else it.path
                    AsyncImage(
                        model = imagePath,
                        contentDescription = "Comic Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }

        }

        var modalVisible by remember { mutableStateOf(false) }
        CommentButtonWithModal(
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterEnd),
            onClick = {
                modalVisible = true
            },
            modalVisible = modalVisible,
            onDismissModalRequest = {
                modalVisible = false
            },
        ) {
            val paddingX = 16.dp
            Scaffold(
                topBar = {
                    CommentModalHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = paddingX),
                        commentCount = comments.size.toLong(),
                        setModalVisible = { modalVisible = it },
                    )
                },
                bottomBar = {
                    var comment by remember { mutableStateOf("") }
                    var isSendError by remember { mutableStateOf(false) }

                    CommentInput(
                        comment = comment,
                        onCommentChange = {
                            isSendError = false
                            comment = it
                        },
                        isError = isSendError,
                        onSendComment = {
                            isSendError = true
                        },
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceDim)
                    )
                },

                ) { innerPadding ->
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(top = 10.dp)
                        .weight(1f)
                ) {
                    items(comments) { (author, content) ->
                        CommentCard(
                            modifier = Modifier
                                .padding(horizontal = paddingX),
                            authorName = author,
                            content = content
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(top = 14.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }

        }
    }
}



