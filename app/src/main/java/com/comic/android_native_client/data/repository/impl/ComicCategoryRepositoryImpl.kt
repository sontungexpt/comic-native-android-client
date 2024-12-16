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
        return try {
            val response = comicCategoryService.fetchComicCategories()
            return when {
                response.isSuccessful -> {
                    response.body()?.let { categoriesResponse ->
                        return Result.Success(categoriesResponse.map { it.toComicCategory() })
                    } ?: Result.NoContent
                }

                response.code() == 404 -> {
                    Result.Error("Categories not found", code = 404)
                }

                response.code() in 400..499 -> {
                    Result.Error("Client error: ${response.message()}", code = response.code())
                }

                else -> {
                    Result.Error("Server error: ${response.message()}", code = response.code())
                }
            }

        } catch (e: Exception) {
            Result.Exception(e)
        }
    }


    override suspend fun addComicCategory(comicCategory: ComicCategoryRequest): Result<ComicCategory> {
        try {
            val response = comicCategoryService.addComicCategory(comicCategory)
            when {
                response.isSuccessful -> {
                    return response.body()?.let { it.toComicCategory() }?.let { Result.Success(it) }
                        ?: Result.NoContent
                }

                else -> {
                    val code = response.code()
                    val message = response.message()
                    when (HttpStatus.from(code)) {
                        HttpStatus.Conflict -> {
                            return Result.Error("Conflict: $message", code = code)
                        }

                        HttpStatus.Forbidden -> {
                            return Result.Error("Forbidden: $message", code = code)
                        }

                        HttpStatus.BadRequest -> {
                            return Result.Error("Bad request: $message", code = code)
                        }

                        else -> {
                            return Result.Error("Server error: $message", code = code)
                        }
                    }

                }
            }
        } catch (e: Exception) {
            return Result.Exception(e)
        }
    }
}