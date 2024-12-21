package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.ComicDetail
import com.comic.android_native_client.data.model.Page

interface ComicRepository {
    suspend fun getComics(
        filterCategoryIds: List<String>? = null,
        page: Int,
        size: Int = 10,
        sort: Array<String> = arrayOf("createdAt"),
    ): Result<Page<Comic>>

    suspend fun getComicDetail(
        comicId: String,
        sourceName: String,
        page: Int,
        size: Int = 10,
        sort: Array<String>? = null,
    ): Result<ComicDetail>

    suspend fun searchComic(
        q: String,
        page: Int,
        size: Int = 10,
        sort: Array<String>? = null,
    ): Result<Page<Comic>>
}