package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.data.model.Chapter

interface ChapterRepository {

    suspend fun getChapter(
        comicId: String,
        chapterId: String
    ): HttpResult<Chapter>

    suspend fun getAllChapters(
        comicId: String
    ): HttpResult<List<Chapter>>
}