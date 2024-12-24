package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comment
import com.comic.android_native_client.data.repository.CommentRepository
import com.comic.android_native_client.network.dto.request.CommentRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {
    val TOP_LEVEL_ID = "0"
    val PAGE_SIZE = 10

    data class Comments(
        val items: List<Comment> = listOf(),
        var loading: Boolean = false,
        var currentPage: Int = -1,
        var total: Int = 0
    )

    private var _comments = mutableStateMapOf<String, Comments>(
        TOP_LEVEL_ID to Comments()
    )

    // input fields
    private var _commentMsg by mutableStateOf("")
    private var _isCommentAdding by mutableStateOf(false)
    private var _isCommentSentError by mutableStateOf(false)
    private var _replyingToId by mutableStateOf<String?>(null)
    private var _replyingToAuthor by mutableStateOf("")

    val comments get() = _comments
    val commentMsg get() = _commentMsg
    val isCommentAdding get() = _isCommentAdding
    val isCommentSentError get() = _isCommentSentError
    val replyingToAuthor get() = _replyingToAuthor

    fun replyingTo(commentId: String, commentAuthor: String) {
        _replyingToId = commentId
        _replyingToAuthor = commentAuthor
    }

    fun resetComments() {
        _comments = mutableStateMapOf(
            TOP_LEVEL_ID to Comments()
        )
    }

    fun clearReplyingTo() {
        _replyingToId = null
        _replyingToAuthor = ""
    }

    fun updateCommentMsg(comment: String) {
        _commentMsg = comment
    }

    fun resetCommentMsg() {
        _isCommentSentError = false
        _commentMsg = ""
        clearReplyingTo()
    }

    fun addComment(
        chapterId: String,
        comicId: String,
    ) {
        if (_commentMsg.isEmpty()) {
            _isCommentSentError = true
            return
        } else if (_isCommentAdding) return
        _isCommentAdding = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = commentRepository.addComment(
                    CommentRequest(
                        content = _commentMsg,
                        replyingTo = _replyingToId,
                        chapterId = chapterId,
                        comicId = comicId
                    )
                )) {
                    is Result.Success -> {
                        val newComment = result.data
                        val mapId = _replyingToId ?: TOP_LEVEL_ID
                        val current = _comments[mapId] ?: Comments()

                        _comments[mapId] = current.copy(
                            items = current.items + newComment,
                            total = current.total + 1
                        )


                        resetCommentMsg()
                        return@launch
                    }

                    is Result.Error -> {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isCommentAdding = false
            }
        }
    }


    fun fetchTopLevelComments(
        comicId: String,
        chapterId: String
    ) {
        if (comments[TOP_LEVEL_ID]!!.loading == true) return
        comments[TOP_LEVEL_ID] = comments[TOP_LEVEL_ID]!!.copy(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nextPage = comments[TOP_LEVEL_ID]!!.currentPage + 1
                when (val result = commentRepository.getTopLevelComments(
                    comicId = comicId,
                    chapterId = chapterId,
                    parentId = null,
                    size = PAGE_SIZE,
                    sort = arrayOf("updatedAt", "id"),
                    page = nextPage,
                )) {
                    is Result.Success -> {
                        val newComments = result.data.content
                        if (newComments.isEmpty()) return@launch
                        val current = comments[TOP_LEVEL_ID]!!
                        _comments[TOP_LEVEL_ID] = current.copy(
                            items = current.items + newComments,
                            total = result.data.totalElements,
                            currentPage = nextPage
                        )
                    }

                    is Result.Error -> {
                        // Handle fetch error
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                comments[TOP_LEVEL_ID] = comments[TOP_LEVEL_ID]!!.copy(
                    loading = false
                )
            }
        }
    }

    fun fetchReplies(parentCommentId: String) {
        if (comments[parentCommentId]?.loading == true) return
        comments[parentCommentId] = (comments[parentCommentId] ?: Comments()).copy(loading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nextPage = (comments[parentCommentId]?.currentPage ?: -1) + 1
                when (val result = commentRepository.getTopLevelReplies(
                    parentId = parentCommentId,
                    page = nextPage,
                    size = PAGE_SIZE,
                    sort = arrayOf("updatedAt", "id")
                )) {

                    is Result.Success -> {
                        val current = comments[parentCommentId] ?: Comments()
                        val newReplies = result.data.content
                        if (newReplies.isEmpty()) return@launch
                        _comments[parentCommentId] = current.copy(
                            items = current.items + newReplies,
                            total = result.data.totalElements,
                            loading = false,
                            currentPage = nextPage
                        )
                    }

                    is Result.Error -> {

                    }
                }
            } catch (e: Exception) {
                _comments[parentCommentId] = _comments[parentCommentId]!!.copy(
                    loading = false,
                )
            }
        }
    }
}