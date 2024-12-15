package com.comic.android_native_client.ui.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    contentWrapperModifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    headerText: String = "Header",
    contentPadding: Dp = 12.dp,
    header: (@Composable () -> Unit) = {
        Text(
            text = headerText,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = contentPadding,
                    vertical = 16.dp
                ),
        )
    },
    content: @Composable (ColumnScope.() -> Unit)
) {
    Scaffold(
        modifier = modifier,
        topBar = header
    ) { innerPadding ->
        Column(
            modifier = contentWrapperModifier
                .padding(innerPadding)
                .padding(horizontal = contentPadding),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            content = content,
        )
    }

}