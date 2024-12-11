package com.comic.android_native_client.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertComment
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentButtonWithModal(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),

    tint: Color = MaterialTheme.colorScheme.primary,
    iconModifier: Modifier = Modifier,

    modalVisible: Boolean,
    onDismissModalRequest: () -> Unit,
    modalSheetState: SheetState = rememberModalBottomSheetState(),
    modalShape: Shape = BottomSheetDefaults.ExpandedShape,
    modalContainerColor: Color = BottomSheetDefaults.ContainerColor,
    modalContentColor: Color = contentColorFor(modalContainerColor),
    modalScrimColor: Color = BottomSheetDefaults.ScrimColor,
    modalTonalElevation: Dp = 0.dp,
    modalDragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    modalCntentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    modalProperties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    modalModifier: Modifier = Modifier,
    modalContent: @Composable() (ColumnScope.() -> Unit),
) {

    if (modalVisible) {
        ModalBottomSheet(
            modifier = modalModifier,
            sheetState = modalSheetState,
            onDismissRequest = onDismissModalRequest,
            shape = modalShape,
            contentColor = modalContentColor,
            containerColor = modalContainerColor,
            tonalElevation = modalTonalElevation,
            scrimColor = modalScrimColor,
            dragHandle = modalDragHandle,
            contentWindowInsets = modalCntentWindowInsets,
            properties = modalProperties,
            content = modalContent
        )
    }
    IconButton(
        colors = colors,
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = Icons.AutoMirrored.Filled.InsertComment,
            contentDescription = "Add a comment",
            tint = tint,
        )
    }
}