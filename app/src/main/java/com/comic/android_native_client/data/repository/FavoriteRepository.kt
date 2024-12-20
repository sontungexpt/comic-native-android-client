package com.comic.android_native_client.data.repository

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.Page

interface FavoriteRepository {
    suspend fun getFavoriteComics(
        page: Int,
        size: Int = 10,
        sort: Array<String>? = null,
    ): Result<Page<Comic>>

    suspend fun addFavorite(comicId: String): Result<Unit>
    suspend fun removeFavorite(comicId: String): Result<Unit>
    suspend fun isFavorite(comicId: String): Result<Boolean>
}