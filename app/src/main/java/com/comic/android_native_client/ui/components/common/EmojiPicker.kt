package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView

@Composable
fun EmojiPicker(
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            EmojiPickerView(context)
        },
        modifier = modifier.fillMaxWidth(),
    )
}

