package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R

@Composable
fun CommentInput(
    replyingTo: String?,

    comment: String,
    sending: Boolean,
    isError: Boolean = false,

    onCommentChange: (String) -> Unit,
    onSendComment: (comment: String) -> Unit,
    onEmptyBackspace: () -> Unit,

    modifier: Modifier = Modifier,
) {

    OutlinedTextField(
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .onKeyEvent {
                if (it.type == KeyEventType.KeyUp) {
                    when (it.key) {
                        Key.Backspace, Key.Delete -> {
                            if (comment.isEmpty()) {
                                onEmptyBackspace()
                                true
                            }
                            false
                        }

                        Key.Enter -> {
                            onSendComment(comment)
                            true
                        }

                        else -> false
                    }
                }
                false
            },
        value = comment,
        onValueChange = onCommentChange,
        isError = isError,
        prefix = {
            if (!replyingTo.isNullOrEmpty()) {
                Text(
                    text = "@ $replyingTo ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        maxLines = 5,
        shape = MaterialTheme.shapes.medium,
        placeholder = {
            Text(
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(R.string.input_comment),
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorBorderColor = MaterialTheme.colorScheme.error,
        ),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = 14.dp),
                onClick = {
                    if (!sending) {
                        onSendComment(comment)
                    }
                }
            ) {
                if (sending) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Gửi"
                    )
                }
            }
        }
    )


}
