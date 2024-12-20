package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.common.Result
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
        sort: Array<String>?
    ): Result<Page<Comic>> {
        val response = favoriteComicService.fetchFavoriteComics(
            page = page,
            size = size,
            sort = sort
        )
        return when {
            response.isSuccessful -> {
                response.body()?.let {
                    Result.Success(it.toPage { it.toComic() })
                } ?: Result.Error(
                    "Error fetching favorite comics",
                    HttpStatus.InternalServerError
                )
            }

            else -> {
                Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }


    override suspend fun addFavorite(comicId: String): Result<Unit> {
        val response = favoriteComicService.addFavoriteComic(comicId)
        return when {
            response.isSuccessful -> {
                Result.Success(Unit)
            }

            else -> {
                Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }
    }

    override suspend fun removeFavorite(comicId: String): Result<Unit> {
        val response = favoriteComicService.removeFavoriteComic(comicId)
        return when {
            response.isSuccessful -> {
                Result.Success(Unit)
            }

            else -> {
                Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }

    override suspend fun isFavorite(comicId: String): Result<Boolean> {
        val response = favoriteComicService.getFavoriteComicStatus(comicId)
        return when {
            response.isSuccessful -> {
                Result.Success(response.body()!!)
            }

            else -> {
                Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }

}


