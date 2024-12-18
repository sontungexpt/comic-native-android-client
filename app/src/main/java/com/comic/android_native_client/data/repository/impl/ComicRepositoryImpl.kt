package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Comic
import com.comic.android_native_client.data.model.ComicDetail
import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.repository.ComicRepository
import com.comic.android_native_client.network.dto.response.toComic
import com.comic.android_native_client.network.dto.response.toComicDetail
import com.comic.android_native_client.network.services.ComicService
import com.comic.android_native_client.network.services.toPage

class ComicRepositoryImpl(
    private val comicService: ComicService
) : ComicRepository {
    override suspend fun getComics(
        filterCategoryIds: List<String>?,
        page: Int,
        size: Int,
        sort: Array<String>
    ): HttpResult<Page<Comic>> {
        val response = comicService.getComics(
            filterCategoryIds = filterCategoryIds,
            page = page,
            size = size,
            sort = sort
        )

        return if (response.isSuccessful) {
            response.body()?.let {
                HttpResult.Success(it.toPage { it.toComic() })
            } ?: HttpResult.Error("Body is empty")
        } else {
            HttpResult.Error(response.message(), HttpStatus.from(response.code()))
        }
    }

    override suspend fun getComicDetail(comicId: String): HttpResult<ComicDetail> {
        val response = comicService.getComicDetail(comicId)

        return if (response.isSuccessful) {
            response.body()?.let {
                HttpResult.Success(it.toComicDetail())
            } ?: HttpResult.Error("Body is empty")
        } else {
            HttpResult.Error(response.message(), HttpStatus.from(response.code()))
        }
    }

    override suspend fun searchComic(
        q: String,
        page: Int,
        size: Int,
        sort: Array<String>
    ): HttpResult<Page<Comic>> {
        val response = comicService.searchComic(
            q = q,
            page = page,
            size = size,
            sort = sort
        )

        return if (response.isSuccessful) {
            response.body()?.let {
                HttpResult.Success(it.toPage { it.toComic() })
            } ?: HttpResult.Error("Body is empty")
        } else {
            HttpResult.Error(response.message(), HttpStatus.from(response.code()))
        }
    }
}