package com.comic.android_native_client.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.TextWithIcon

@Composable
fun CommentCard(
    authorName: String,
    updatedAt: String,
    numberOfReplies: Int,
    authorAvatar: String = "",
    content: String,

    onReplyClick: () -> Unit,
    onShowReplies: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
        modifier = modifier.fillMaxWidth()
    ) {

        Avatar(
            modifier = Modifier
                .fillMaxWidth(0.12f)
                .aspectRatio(1f)
                .offset(y = 4.dp)
                .clip(CircleShape), imageUrl = authorAvatar
        )

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Author Name
            Text(
                text = authorName,
                style = MaterialTheme.typography.labelSmall.copy(
                    lineHeight = TextUnit.Unspecified,
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Content
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Actions Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        onReplyClick()
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .offset(x = -8.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.reply),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = updatedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            TextButton(
                enabled = numberOfReplies > 0,
                onClick = {
                    onShowReplies()
                },
                modifier = Modifier.height(30.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = MaterialTheme.colorScheme.outline
                )
            ) {
                TextWithIcon(
                    suffixIcon = Icons.Default.ArrowDropDown,
                    suffixIconModifier = Modifier.size(40.dp),
                    text = stringResource(R.string.view_replys, numberOfReplies),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
