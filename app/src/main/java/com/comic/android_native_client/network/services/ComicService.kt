package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.ComicDetailResponse
import com.comic.android_native_client.network.dto.response.ComicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicComicService {
    @GET(MainEndpoint.GET_COMICS_V1)
    suspend fun getComics(
        @Query("filterCategoryIds") filterCategoryIds: List<String> = emptyList(),
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Array<String> = arrayOf("createdAt"),
    ): Response<PageResponse<ComicResponse>>

    @GET(MainEndpoint.GET_COMIC_DETAIL_V1)
    suspend fun getComicDetail(
        @Path("comicId") comicId: String,
    ): Response<ComicDetailResponse>

    @GET(MainEndpoint.SEARCH_COMICS_V1)
    suspend fun searchComic(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: Array<String> = arrayOf("createdAt"),
    ): Response<PageResponse<ComicResponse>>
}

interface ComicService : PublicComicService
