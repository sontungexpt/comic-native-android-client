package com.comic.android_native_client.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.TextWithIcon

@Composable
fun CommentCard(
    authorName: String,
    authorAvatar: String = "",
    content: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
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
                style = MaterialTheme.typography.labelLarge.copy(
                    lineHeight = TextUnit.Unspecified,
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Content
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Actions Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .offset(x = -8.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.reply),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "09-12",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // View Replies Button
            TextWithIcon(
                suffixIcon = Icons.Default.ArrowDropDown,
                suffixIconModifier = Modifier.size(20.dp),
                suffixIconTint = MaterialTheme.colorScheme.primary,
                text = stringResource(R.string.view_replys, 3),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
