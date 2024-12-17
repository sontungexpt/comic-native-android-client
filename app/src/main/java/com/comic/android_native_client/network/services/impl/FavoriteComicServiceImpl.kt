package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.network.dto.response.ComicResponse
import com.comic.android_native_client.network.services.AuthenticatedFavoriteComicService
import com.comic.android_native_client.network.services.FavoriteComicService
import com.comic.android_native_client.network.services.PageResponse
import retrofit2.Response
import retrofit2.Retrofit

class FavoriteComicServiceImpl(
    private val authenticatedRetrofit: Retrofit
) : FavoriteComicService {

    private val favoriteComicServiceAuthenticated by lazy {
        authenticatedRetrofit.create(AuthenticatedFavoriteComicService::class.java)
    }

    override suspend fun fetchFavoriteComics(
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<ComicResponse>> {
        return favoriteComicServiceAuthenticated.fetchFavoriteComics(
            page = page,
            size = size,
            sort = sort
        )
    }

    override suspend fun addFavoriteComic(comicId: String): Response<Nothing> {
        return favoriteComicServiceAuthenticated.addFavoriteComic(comicId)
    }

    override suspend fun removeFavoriteComic(comicId: String): Response<Nothing> {
        return favoriteComicServiceAuthenticated.removeFavoriteComic(comicId)
    }

    override suspend fun getFavoriteComicStatus(comicId: String): Response<Boolean> {
        return favoriteComicServiceAuthenticated.getFavoriteComicStatus(comicId)
    }

}
