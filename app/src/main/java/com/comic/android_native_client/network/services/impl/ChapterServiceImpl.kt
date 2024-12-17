package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.network.dto.response.ChapterResponse
import com.comic.android_native_client.network.dto.response.ShortInfoChapterResponse
import com.comic.android_native_client.network.services.ChapterService
import com.comic.android_native_client.network.services.PublicChapterService
import retrofit2.Response
import retrofit2.Retrofit

class ChapterServiceImpl(
    private val publicClientRetrofit: Retrofit
) : ChapterService {

    private val publicChapterService by lazy {
        publicClientRetrofit.create(PublicChapterService::class.java)
    }

    override suspend fun getAllChapters(comicId: String): Response<List<ShortInfoChapterResponse>> {
        return publicChapterService.getAllChapters(comicId)
    }

    override suspend fun getChapterDetail(
        comicId: String,
        chapterId: String
    ): Response<ChapterResponse> {
        return publicChapterService.getChapterDetail(comicId, chapterId)
    }
}