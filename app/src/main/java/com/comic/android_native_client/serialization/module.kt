package com.comic.android_native_client.serialization

import com.comic.android_native_client.network.dto.response.ChapterResponse
import com.comic.android_native_client.network.dto.response.ComicChapterResponse
import com.comic.android_native_client.network.dto.response.NovelChapterResponse
import com.comic.android_native_client.network.dto.response.ResourceInfoResponse
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val module = SerializersModule {
    polymorphic(ResourceInfoResponse::class) {
        subclass(ResourceInfoResponse.Absolute::class)
        subclass(ResourceInfoResponse.Relative::class)
    }

    polymorphic(ChapterResponse::class) {
        subclass(ComicChapterResponse::class)
        subclass(NovelChapterResponse::class)
    }

}
