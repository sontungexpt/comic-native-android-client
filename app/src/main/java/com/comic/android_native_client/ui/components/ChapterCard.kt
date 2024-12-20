package com.comic.android_native_client.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.comic.android_native_client.ui.components.common.TextWithIcon

@Composable
fun ChapterCard(
    name: String,
    number: String,
    imageUrl: String,
    updateDate: String,
    onClick: () -> Unit = { },
    border: BorderStroke = BorderStroke(
        1.dp, MaterialTheme.colorScheme.outline,
    ),
    shape: Shape = MaterialTheme.shapes.small,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        border = border,
        shape = shape,
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = modifier
                .padding(12.dp)
                .clickable {
                },
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Chapter Image",
                modifier = Modifier
                    .fillMaxWidth(0.21f)
                    .height(90.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.FillHeight
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Chapter $number",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (name.isNotEmpty()) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextWithIcon(
                    prefixIcon = Icons.Filled.Update,
                    text = "$updateDate",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
