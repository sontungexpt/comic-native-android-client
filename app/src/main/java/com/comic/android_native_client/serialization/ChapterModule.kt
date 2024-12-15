package com.comic.android_native_client.serialization

import com.comic.android_native_client.network.dto.response.ChapterResponse
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val chapterModule = SerializersModule {
    polymorphic(ChapterResponse::class) {
//        subclass(ComicChapterResponse::class)
//        subclass(NovelChapterResponse::class)
    }
}
