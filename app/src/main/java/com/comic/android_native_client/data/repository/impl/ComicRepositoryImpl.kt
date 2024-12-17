package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.data.repository.ComicRepository
import com.comic.android_native_client.network.dto.response.ComicDetailResponse
import com.comic.android_native_client.network.dto.response.ComicResponse
import com.comic.android_native_client.network.services.ComicService
import com.comic.android_native_client.network.services.PageResponse
import retrofit2.Response

class ComicRepositoryImpl(
    private val comicService: ComicService
) : ComicRepository {
    override suspend fun getComics(
        filterCategoryIds: List<String>,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<ComicResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getComicDetail(comicId: String): Response<ComicDetailResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchComic(
        q: String,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<ComicResponse>> {
        TODO("Not yet implemented")
    }
}