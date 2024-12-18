package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.repository.FavoriteRepository
import com.comic.android_native_client.network.dto.response.toComic
import com.comic.android_native_client.network.services.FavoriteComicService
import com.comic.android_native_client.network.services.toPage

class FavoriteRepositoryImpl(
    private val favoriteComicService: FavoriteComicService
) : FavoriteRepository {

    override suspend fun getFavoriteComics(
        page: Int,
        size: Int,
        sort: Array<String>
    ): HttpResult<Page<Comic>> {
        val response = favoriteComicService.fetchFavoriteComics(
            page = page,
            size = size,
            sort = sort
        )
        return when {
            response.isSuccessful -> {
                response.body()?.let {
                    HttpResult.Success(it.toPage { it.toComic() })
                } ?: HttpResult.Error(
                    "Error fetching favorite comics",
                    HttpStatus.InternalServerError
                )
            }

            else -> {
                HttpResult.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }


    override suspend fun addFavorite(comicId: String): HttpResult<Unit> {
        val response = favoriteComicService.addFavoriteComic(comicId)
        return when {
            response.isSuccessful -> {
                HttpResult.Success(Unit)
            }

            else -> {
                HttpResult.Error(response.message(), HttpStatus.from(response.code()))
            }
        }
    }

    override suspend fun removeFavorite(comicId: String): HttpResult<Unit> {
        val response = favoriteComicService.removeFavoriteComic(comicId)
        return when {
            response.isSuccessful -> {
                HttpResult.Success(Unit)
            }

            else -> {
                HttpResult.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }

    override suspend fun isFavorite(comicId: String): HttpResult<Boolean> {
        val response = favoriteComicService.getFavoriteComicStatus(comicId)
        return when {
            response.isSuccessful -> {
                HttpResult.Success(response.body()!!)
            }

            else -> {
                HttpResult.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }

}


