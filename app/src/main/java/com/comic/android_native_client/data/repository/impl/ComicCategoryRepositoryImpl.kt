package com.comic.android_native_client.data.repository.impl


import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.model.ComicCategory
import com.comic.android_native_client.data.repository.ComicCategoryRepository
import com.comic.android_native_client.network.dto.request.ComicCategoryRequest
import com.comic.android_native_client.network.dto.response.toComicCategory
import com.comic.android_native_client.network.services.ComicCategoryService

class ComicCategoryRepositoryImpl(
    private val comicCategoryService: ComicCategoryService
) : ComicCategoryRepository {

    override suspend fun getComicCategories(): HttpResult<List<ComicCategory>> {
        val response = comicCategoryService.fetchComicCategories()
        return when {
            response.isSuccessful -> {
                response.body()?.let { categoriesResponse ->
                    return HttpResult.Success(categoriesResponse.map { it.toComicCategory() })
                } ?: HttpResult.Error("Error fetching categories", HttpStatus.InternalServerError)
            }

            else -> {
                val message = response.message()
                when (val status = HttpStatus.from(response.code())) {
                    HttpStatus.NotFound -> {
                        HttpResult.Error("Categories not found", status)
                    }

                    else -> {
                        HttpResult.Error(message, status)
                    }
                }
            }
        }
    }


    override suspend fun addComicCategory(comicCategory: ComicCategoryRequest): HttpResult<ComicCategory> {
        val response = comicCategoryService.addComicCategory(comicCategory)
        when {
            response.isSuccessful -> {
                return response.body()?.let { it.toComicCategory() }?.let { HttpResult.Success(it) }
                    ?: HttpResult.Error("Error adding category", HttpStatus.InternalServerError)
            }

            else -> {
                val message = response.message()
                when (val status = HttpStatus.from(response.code())) {
                    HttpStatus.Conflict -> {
                        return HttpResult.Error("Conflict: $message", status)
                    }

                    HttpStatus.Forbidden -> {
                        return HttpResult.Error("Forbidden: $message", status)
                    }

                    HttpStatus.BadRequest -> {
                        return HttpResult.Error("Bad request: $message", status)
                    }

                    else -> {
                        return HttpResult.Error(message, status)
                    }
                }

            }
        }

    }
}