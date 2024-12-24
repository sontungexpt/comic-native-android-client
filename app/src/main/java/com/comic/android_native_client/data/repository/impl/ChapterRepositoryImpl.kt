package com.comic.android_native_client.data.repository.impl

import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.repository.ChapterRepository
import com.comic.android_native_client.network.dto.response.toChapter
import com.comic.android_native_client.network.services.ChapterService

class ChapterRepositoryImpl(
    private val chapterService: ChapterService
) : ChapterRepository {
    override suspend fun getChapter(comicId: String, chapterId: String): Result<Chapter> {
        val response = chapterService.getChapterDetail(comicId, chapterId)
        when {
            response.isSuccessful -> {
                return response.body()?.let {
                    Result.Success(it.toChapter())
                } ?: Result.Error("No chapter found")
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }
    }

    override suspend fun getAllChapters(comicId: String): Result<List<Chapter>> {
        val response = chapterService.getAllChapters(comicId)
        when {
            response.isSuccessful -> {
                return response.body()?.let {
                    Result.Success(it.map { it.toChapter() })
                } ?: Result.Error("No chapters found")
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }
    }

    override suspend fun getLastestReadChapterDetail(comicId: String): Result<Chapter> {
        val response = chapterService.getLastestReadChapterDetail(comicId)
        when {
            response.isSuccessful -> {
                return response.body()?.let {
                    Result.Success(it.toChapter())
                } ?: Result.Error("No chapter found")
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }

    }

    override suspend fun getFirstChapterDetail(comicId: String): Result<Chapter> {
        val response = chapterService.getFirstChapterDetail(comicId)
        when {
            response.isSuccessful -> {
                return response.body()?.let {
                    Result.Success(it.toChapter())
                } ?: Result.Error("No chapter found")
            }

            else -> {
                return Result.Error(response.message(), HttpStatus.from(response.code()))
            }
        }
    }
}