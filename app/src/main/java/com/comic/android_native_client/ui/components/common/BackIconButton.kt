package com.comic.android_native_client.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.comic.android_native_client.R

@Composable
fun BackIconButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    IconButton(
        onClick = onBackClick,
        modifier = modifier
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Biểu tượng mũi tên
            contentDescription = stringResource(R.string.back),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}