package com.comic.android_native_client.ui.activities.index.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SectionDivider() {
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outline
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun GenreTag(
    name: String,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
