package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TitleContentSection(
    title: String,
    titleModifier: Modifier = Modifier.padding(bottom = 6.dp),
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    content: String,
    contentModifier: Modifier = Modifier.padding(bottom = 16.dp),
    contextStyle: TextStyle = MaterialTheme.typography.bodySmall
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = titleStyle,
            modifier = titleModifier
        )
        Text(
            text = content,
            style = contextStyle,
            modifier = contentModifier
        )
    }
}