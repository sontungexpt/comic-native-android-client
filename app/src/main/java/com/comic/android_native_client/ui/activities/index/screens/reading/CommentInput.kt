package com.comic.android_native_client.ui.activities.index.screens.reading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R

@Composable
fun CommentInput(
    comment: String,
    isError: Boolean = false,
    onCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
    textInputModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = comment,
            onValueChange = onCommentChange,
            isError = isError,
            maxLines = 5,
            modifier = textInputModifier
                .weight(1f),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    fontSize = 12.sp,
                    text = stringResource(R.string.input_comment),
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                errorLabelColor = MaterialTheme.colorScheme.error,
                errorBorderColor = MaterialTheme.colorScheme.error,
            ),

            )

        IconButton(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .padding(start = 4.dp),
            onClick = onSendComment
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Gửi")
        }
    }
}