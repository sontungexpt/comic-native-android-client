package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.ChapterResponse
import com.comic.android_native_client.network.dto.response.ShortInfoChapterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicChapterService {
    @GET(MainEndpoint.GET_ALL_CHAPTERS_V1)
    suspend fun getAllChapters(
        @Path("comicId") comicId: String,
    ): Response<List<ShortInfoChapterResponse>>

    @GET(MainEndpoint.GET_CHAPTER_DETAIL_V1)
    suspend fun getChapterDetail(
        @Path("comicId") comicId: String,
        @Path("chapterId") chapterId: String,
    ): Response<ChapterResponse>

    @GET(MainEndpoint.GET_LASTEST_READ_CHAPTER)
    suspend fun getLastestReadChapterDetail(
        @Path("comicId") comicId: String,
    ): Response<ChapterResponse>

    @GET(MainEndpoint.GET_FIRST_CHAPTER)
    suspend fun getFirstChapterDetail(
        @Path("comicId") comicId: String,
    ): Response<ChapterResponse>
}


interface ChapterService : PublicChapterService