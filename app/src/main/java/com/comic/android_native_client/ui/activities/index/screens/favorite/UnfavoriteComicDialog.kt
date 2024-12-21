package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.LoadingIndicatorTextButton

@Composable
fun UnfavoriteComicDiaglog(
    title: String = stringResource(R.string.unfavorite),
    message: String = stringResource(R.string.unfavorite_message),
    onDismiss: () -> Unit,
    onAgreement: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 10.dp,
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        modifier = modifier.wrapContentSize(),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            LoadingIndicatorTextButton(
                loadingModifier = Modifier.size(24.dp),
                loading = loading,
                onClick = {
                    onAgreement()
                }) {
                Text(
                    color = MaterialTheme.colorScheme.error,
                    text = stringResource(R.string.unfavorite),
                    style = MaterialTheme.typography.labelLarge
                )
            }

        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    )
}
