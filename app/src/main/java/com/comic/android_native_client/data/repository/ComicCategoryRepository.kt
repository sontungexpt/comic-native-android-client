package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.data.model.ComicCategory
import com.comic.android_native_client.network.dto.request.ComicCategoryRequest

interface ComicCategoryRepository {

    suspend fun getComicCategories(): HttpResult<List<ComicCategory>>

    suspend fun addComicCategory(comicCategory: ComicCategoryRequest): HttpResult<ComicCategory>

}

