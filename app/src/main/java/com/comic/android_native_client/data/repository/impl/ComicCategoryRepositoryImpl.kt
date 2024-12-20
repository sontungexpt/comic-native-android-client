package com.comic.android_native_client.data.repository.impl


import com.comic.android_native_client.common.Result
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.ComicCategory
import com.comic.android_native_client.data.repository.ComicCategoryRepository
import com.comic.android_native_client.network.dto.request.ComicCategoryRequest
import com.comic.android_native_client.network.dto.response.toComicCategory
import com.comic.android_native_client.network.services.ComicCategoryService

class ComicCategoryRepositoryImpl(
    private val comicCategoryService: ComicCategoryService
) : ComicCategoryRepository {

    override suspend fun getComicCategories(): Result<List<ComicCategory>> {
        val response = comicCategoryService.fetchComicCategories()
        return when {
            response.isSuccessful -> {
                response.body()?.let { categoriesResponse ->
                    return Result.Success(categoriesResponse.map { it.toComicCategory() })
                } ?: Result.Error("Error fetching categories", HttpStatus.InternalServerError)
            }

            else -> {
                val message = response.message()
                when (val status = HttpStatus.from(response.code())) {
                    HttpStatus.NotFound -> {
                        Result.Error("Categories not found", status)
                    }

                    else -> {
                        Result.Error(message, status)
                    }
                }
            }
        }
    }


    override suspend fun addComicCategory(comicCategory: ComicCategoryRequest): Result<ComicCategory> {
        val response = comicCategoryService.addComicCategory(comicCategory)
        when {
            response.isSuccessful -> {
                return response.body()?.let { it.toComicCategory() }?.let { Result.Success(it) }
                    ?: Result.Error("Error adding category", HttpStatus.InternalServerError)
            }

            else -> {
                val message = response.message()
                when (val status = HttpStatus.from(response.code())) {
                    HttpStatus.Conflict -> {
                        return Result.Error("Conflict: $message", status)
                    }

                    HttpStatus.Forbidden -> {
                        return Result.Error("Forbidden: $message", status)
                    }

                    HttpStatus.BadRequest -> {
                        return Result.Error("Bad request: $message", status)
                    }

                    else -> {
                        return Result.Error(message, status)
                    }
                }

            }
        }

    }
}