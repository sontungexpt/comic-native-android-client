package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.network.dto.response.ComicResponse
import com.comic.android_native_client.network.services.AuthenticatedFavoriteComicService
import com.comic.android_native_client.network.services.FavoriteComicService
import retrofit2.Response
import retrofit2.Retrofit

class FavoriteComicServiceImpl(
    private val authenticatedRetrofit: Retrofit
) : FavoriteComicService {

    private val favoriteComicServiceAuthenticated by lazy {
        authenticatedRetrofit.create(AuthenticatedFavoriteComicService::class.java)
    }

    override suspend fun fetchFavoriteComics(): Response<ComicResponse> {
        return favoriteComicServiceAuthenticated.fetchFavoriteComics()
    }

    override suspend fun addFavoriteComic(comicId: String): Response<Nothing> {
        return favoriteComicServiceAuthenticated.addFavoriteComic(comicId)
    }

    override suspend fun removeFavoriteComic(comicId: String): Response<Nothing> {
        return favoriteComicServiceAuthenticated.removeFavoriteComic(comicId)
    }

    override suspend fun getFavoriteComicStatus(comicId: String): Response<Nothing> {
        return favoriteComicServiceAuthenticated.getFavoriteComicStatus(comicId)
    }

}
