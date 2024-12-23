package com.comic.android_native_client.ui.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class LoadMoreListState<out T>(
    val items: List<T> = mutableStateListOf(),
    var loading: MutableState<Boolean> = mutableStateOf<Boolean>(false),
    var total: MutableState<Int> = mutableStateOf(0),
)
