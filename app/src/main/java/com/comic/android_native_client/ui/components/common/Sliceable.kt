package com.comic.android_native_client.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.common.Identifiable


@Composable
fun <ID : Any, T : Identifiable<ID>> Sliceable(
    state: LazyListState = rememberLazyListState(),
    reverseLayout: Boolean = false,

    loading: Boolean = false,
    items: List<T> = emptyList(),
    gap: Dp = 10.dp,
    heightPrediction: Dp = 200.dp,
    modifier: Modifier = Modifier.fillMaxWidth(),
    itemsWrapperModifier: Modifier = Modifier.fillMaxWidth(),

    headerGap: Dp = 16.dp,
    header: @Composable() (ColumnScope.() -> Unit) = { },

    content: @Composable() (LazyItemScope.(item: T) -> Unit)

) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(headerGap)
    ) {
        header()
        if (loading) {
            LoadingCircle(
                wrapperModifier = Modifier
                    .height(heightPrediction)
                    .fillMaxWidth(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 28.dp),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            LazyRow(
                state = state,
                reverseLayout = reverseLayout,
                modifier = itemsWrapperModifier,
                horizontalArrangement = Arrangement.spacedBy(gap),
            ) {
                items(
                    items = items,
                    key = { it.id },
                    contentType = { it.javaClass },
                    itemContent = content
                )
            }

        }


    }

}