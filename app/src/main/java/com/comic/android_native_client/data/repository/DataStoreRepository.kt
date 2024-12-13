package com.comic.android_native_client.data.repository

import com.comic.android_native_client.constants.DataStoreName

interface DataStoreRepository {
    fun getPreferencesFile(): DataStoreName
}
