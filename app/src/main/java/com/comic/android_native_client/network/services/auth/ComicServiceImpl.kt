package com.comic.android_native_client.network.services.auth

import com.comic.android_native_client.network.dto.response.ComicDetailResponse
import com.comic.android_native_client.network.dto.response.ComicResponse
import com.comic.android_native_client.network.services.ComicService
import com.comic.android_native_client.network.services.PageResponse
import com.comic.android_native_client.network.services.PublicComicService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ComicServiceImpl @Inject constructor(
    private val publicClientRetrofit: Retrofit
) : ComicService {

    private val publicComicService by lazy {
        publicClientRetrofit.create(PublicComicService::class.java)
    }

    override suspend fun getComics(
        filterCategoryIds: List<String>,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<ComicResponse>> {
        return publicComicService.getComics(
            filterCategoryIds, page, size, sort
        )
    }

    override suspend fun getComicDetail(comicId: String): Response<ComicDetailResponse> {
        return publicComicService.getComicDetail(comicId)
    }

    override suspend fun searchComic(
        q: String,
        page: Int,
        size: Int,
        sort: Array<String>
    ): Response<PageResponse<ComicResponse>> {
        return publicComicService.searchComic(q, page, size, sort)
    }


}