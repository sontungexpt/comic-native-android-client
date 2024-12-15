package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.ComicCategory

interface ComicCategoryRepository {

    suspend fun getComicCategories(): Result<List<ComicCategory>>

    suspend fun addComicCategory(comicCategory: ComicCategory)
}

