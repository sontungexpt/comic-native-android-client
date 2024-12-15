package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.common.Identifiable


interface SliceableItem<T> : Identifiable<T>

@Composable
fun <ID : Any, T : SliceableItem<ID>> Sliceable(
    state: LazyListState = rememberLazyListState(),
    reverseLayout: Boolean = false,

    loading: Boolean = false,
    items: List<T> = emptyList(),
    gap: Dp = 12.dp,
    modifier: Modifier = Modifier,
    itemsWrapperModifier: Modifier = Modifier,

    label: String? = null,
    labelGap: Dp = 16.dp,
    labelFontSize: TextUnit = TextUnit.Unspecified,
    labelFontFamily: FontFamily? = null,
    labelFontWeight: FontWeight? = null,
    labelFontStyle: FontStyle? = null,
    labelLineHeight: TextUnit = TextUnit.Unspecified,
    labelStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    labelModifier: Modifier = Modifier,

    content: @Composable (T) -> Unit,

    ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(labelGap)
    ) {
        if (label != null) {
            Text(
                text = label,
                style = labelStyle,
                fontSize = labelFontSize,
                lineHeight = labelLineHeight,
                fontWeight = labelFontWeight,
                fontFamily = labelFontFamily,
                fontStyle = labelFontStyle,
                modifier = labelModifier
            )
        }

        LazyRow(
            state = state,
            reverseLayout = reverseLayout,
            modifier = itemsWrapperModifier,
            horizontalArrangement = Arrangement.spacedBy(gap),
        ) {
            if (loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(vertical = 34.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                items(
                    items = items,
                    key = { item -> item.id }
                ) { item ->
                    content(item)
                }
            }

        }


    }

}