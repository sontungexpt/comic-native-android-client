package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateCircularIndicator(modifier: Modifier = Modifier) {
    val (loading, setLoading) = remember { mutableStateOf(false) }

    Button(modifier = modifier, onClick = { setLoading(true) }, enabled = !loading) {
        Text("Start loading")
        if (!loading) return@Button

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }


}


@Preview
@Composable
fun IndeterminateCircularIndicatorPreview() {
    IndeterminateCircularIndicator()
}