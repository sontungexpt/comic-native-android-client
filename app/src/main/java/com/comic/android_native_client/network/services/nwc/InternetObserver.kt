package com.comic.android_native_client.network.services.nwc

import kotlinx.coroutines.flow.Flow


interface InternetObserver {
    val connected: Flow<Boolean>
}