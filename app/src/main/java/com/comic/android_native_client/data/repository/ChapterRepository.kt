package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Chapter

interface ChapterRepository {

    suspend fun getChapter(
        comicId: String,
        chapterId: String
    ): Result<Chapter>

    suspend fun getAllChapters(
        comicId: String
    ): Result<List<Chapter>>
}