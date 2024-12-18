package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.Page

interface FavoriteRepository {
    suspend fun getFavoriteComics(
        page: Int,
        size: Int = 10,
        sort: Array<String> = arrayOf("createdAt,desc")
    ): HttpResult<Page<Comic>>

    suspend fun addFavorite(comicId: String): HttpResult<Unit>
    suspend fun removeFavorite(comicId: String): HttpResult<Unit>
    suspend fun isFavorite(comicId: String): HttpResult<Boolean>
}