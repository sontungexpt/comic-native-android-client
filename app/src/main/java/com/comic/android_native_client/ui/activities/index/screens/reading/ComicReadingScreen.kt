package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.model.ComicChapter
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.model.NovelChapter
import com.comic.android_native_client.network.dto.response.ResourceInfo
import com.comic.android_native_client.ui.components.CommentButtonWithModal
import com.comic.android_native_client.ui.components.CommentCard
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.BackFloatingScreen
import com.comic.android_native_client.ui.utils.formatTimeAgo


fun getChapterName(chapter: Chapter): String {
    return if (chapter.name.isNotBlank())
        "Chapter ${chapter.num}: ${chapter.name}"
    else "Chapter ${chapter.num}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicReadingScreen(
    horizontalPadding: Dp = 20.dp,
    commentViewModel: CommentViewModel = hiltViewModel<CommentViewModel>(),
    comicReadingViewModel: ComicReadingViewModel = hiltViewModel<ComicReadingViewModel>(),
    navController: NavController,
    currentChapter: Screen.ComicReading,
) {
    val context = LocalContext.current
    val uiState by comicReadingViewModel.uiState.collectAsState()
    val couroutineScope = rememberCoroutineScope()
    var chapterListDiaglogOpened by remember { mutableStateOf(false) }

    val chapterName = if (uiState.chapter != null)
        getChapterName(uiState.chapter!!)
    else ""


    fun handleNotFound() {
        navController.navigate(Screen.NotFound) {
            popUpTo(Screen.Home) {
                inclusive = true
            }
        }
    }

    // initial effect
    LaunchedEffect(
        currentChapter.chapterId,
        currentChapter.comicId
    ) {
        if (currentChapter.lastestRead) {
            comicReadingViewModel.loadLastestReadChapter(
                comicId = currentChapter.comicId,
                onNotFound = { handleNotFound() }
            )
        } else {
            comicReadingViewModel.loadChapter(
                comicId = currentChapter.comicId,
                chapterId = currentChapter.chapterId,
                onNotFound = { handleNotFound() }
            )
        }
        comicReadingViewModel.loadAllChapters(
            comicId = currentChapter.comicId,
            onNotFound = { handleNotFound() }
        )
    }

    // the effect when chapter is loaded
    LaunchedEffect(uiState.chapter, currentChapter.comicId) {
        if (uiState.chapter != null) {
            commentViewModel.resetComments()
            commentViewModel.fetchTopLevelComments(
                comicId = currentChapter.comicId,
                chapterId = uiState.chapter!!.id
            )

        }
    }

    BackFloatingScreen(
        onBackCLick = {
            navController.popBackStack()

        },
        modifier = Modifier.fillMaxSize()
    ) {
        if (chapterListDiaglogOpened) {
            Dialog(
                onDismissRequest = {
                    chapterListDiaglogOpened = false
                },
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .zIndex(1000f)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.medium
                        )
                        .fillMaxWidth(0.96f)
                        .background(MaterialTheme.colorScheme.surface)
                        .align(Alignment.Center)
                        .defaultMinSize(
                            minHeight = 200.dp,
                        )
                ) {
                    items(
                        key = { it.id },
                        contentType = { it.javaClass },
                        items = uiState.chapterList
                    ) {
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = getChapterName(it),
                        )
                    }
                }
            }

        }


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
            onClickName = {
                if (uiState.chapterList.isNotEmpty()) {
                    chapterListDiaglogOpened = !chapterListDiaglogOpened
                }
            },
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
                    .padding(
                        vertical = 10.dp,
                        horizontal = horizontalPadding
                    )
            )

            Text(
                textAlign = TextAlign.Justify,
                text = chapter.content,
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
            )
        } else if (uiState.chapter is ComicChapter) {
            val comicChapter = uiState.chapter as ComicChapter
            var scale by remember { mutableStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                scale *= zoomChange
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .graphicsLayer(
                        scaleX = maxOf(1f, minOf(scale, 3f)),
                        scaleY = maxOf(1f, minOf(scale, 3f)),
                    )
                    .transformable(state = state)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    key = { it.number },
                    contentType = { it.javaClass },
                    items = comicChapter.imagePages,
                ) {
                    AsyncImage(
                        model = if (comicChapter.resourceInfo is ResourceInfo.Relative)
                            comicChapter.resourceInfo.baseUrl + "/" + it.path
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

        var modalVisible by remember { mutableStateOf(false) }
        CommentButtonWithModal(
            enabled = uiState.chapter != null,
            colors = IconButtonDefaults.iconButtonColors(
                disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                contentColor = MaterialTheme.colorScheme.primary,
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


            val paddingX = 16
            Scaffold(
                topBar = {
                    CommentModalHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = paddingX.dp
                            ),
                        commentCount = commentViewModel.comments[commentViewModel.TOP_LEVEL_ID]!!.total,
                        setModalVisible = { modalVisible = it },
                    )
                },
                bottomBar = {
                    CommentInput(
                        enabled = uiState.chapter != null &&
                                commentViewModel.commentMsg.isNotBlank(),
                        comment = commentViewModel.commentMsg,
                        onCommentChange = {
                            commentViewModel.updateCommentMsg(it)
                        },
                        onEmptyBackspace = {
                            commentViewModel.clearReplyingTo()
                        },
                        isError = commentViewModel.isCommentSentError,
                        sending = commentViewModel.isCommentAdding,
                        onSendComment = {
                            commentViewModel.addComment(
                                chapterId = uiState.chapter!!.id,
                                comicId = currentChapter.comicId
                            )
                        },
                        replyingTo = commentViewModel.replyingToAuthor,
                        modifier = Modifier
                            .fillMaxWidth()
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
                    fun recusiveComment(comment: Comment, depth: Int = 0) {
                        val id = comment.id
                        item(
                            key = id,
                            contentType = comment.javaClass
                        ) {
                            CommentCard(
                                modifier = Modifier
                                    .padding(
                                        end = paddingX.dp,
                                        start = (paddingX + depth * 32).dp
                                    ),
                                updatedAt = formatTimeAgo(context, comment.updatedAt),
                                numberOfReplies = comment.totalReplies,
                                authorName = comment.author.name,
                                content = comment.content,
                                authorAvatar = comment.author.avatar,
                                onReplyClick = {
                                    commentViewModel.replyingTo(comment.id, comment.author.name)
                                },
                                onShowReplies = {
                                    println("fetch replies")
                                    commentViewModel.fetchReplies(parentCommentId = id)
                                }
                            )
                        }
                        commentViewModel.comments.get(id)?.let {
                            for (reply in it.items) {
                                recusiveComment(reply, depth + 1)
                            }
                        }
                    }

                    for (comment in commentViewModel.comments[commentViewModel.TOP_LEVEL_ID]!!.items) {
                        recusiveComment(comment, 0)

                        item(
                            key = "bottomDivider ${comment.id}",
                            contentType = "bottomDivider"
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.padding(
                                    top = 10.dp
                                ),
                                thickness = 2.dp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }

                    item(
                        key = "loadMoreComment",
                        contentType = "loadMoreComment"
                    ) {
                        LaunchedEffect(uiState.chapter) {
                            commentViewModel.fetchTopLevelComments(
                                comicId = currentChapter.comicId,
                                chapterId = uiState.chapter!!.id
                            )
                        }
                        LoadingCircle(
                            loading = commentViewModel.comments[commentViewModel.TOP_LEVEL_ID]!!.loading,
                        )

                    }

                }
            }
        }
    }
}



