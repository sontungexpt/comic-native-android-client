package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthenticatedFavoriteComicService {

    @GET(MainEndpoint.GET_FAVORITE_COMICS_V1)
    suspend fun fetchFavoriteComics(
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Array<String>? = null
    ): Response<PageResponse<ComicResponse>>

    @POST(MainEndpoint.ADD_FAVORITE_COMIC_V1)
    suspend fun addFavoriteComic(
        @Path("comicId") comicId: String
    ): Response<Unit>

    @POST(MainEndpoint.REMOVE_FAVORITE_COMIC_V1)
    suspend fun removeFavoriteComic(
        @Path("comicId") comicId: String
    ): Response<Unit>

    @GET(MainEndpoint.GET_FAVORITE_COMIC_STATUS_V1)
    suspend fun getFavoriteComicStatus(
        @Path("comicId") comicId: String
    ): Response<Boolean>

}

interface FavoriteComicService : AuthenticatedFavoriteComicService