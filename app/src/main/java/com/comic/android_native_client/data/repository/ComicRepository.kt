package com.comic.android_native_client.data.repository

import com.comic.android_native_client.network.dto.response.ComicDetailResponse
import com.comic.android_native_client.network.dto.response.ComicResponse
import com.comic.android_native_client.network.services.PageResponse
import retrofit2.Response

interface ComicRepository {
    suspend fun getComics(
        filterCategoryIds: List<String> = emptyList(),
        page: Int,
        size: Int = 10,
        sort: Array<String> = arrayOf("createdAt"),
    ): Response<PageResponse<ComicResponse>>

    suspend fun getComicDetail(
        comicId: String,
    ): Response<ComicDetailResponse>

    suspend fun searchComic(
        q: String,
        page: Int,
        size: Int = 10,
        sort: Array<String> = arrayOf("createdAt"),
    ): Response<PageResponse<ComicResponse>>
}