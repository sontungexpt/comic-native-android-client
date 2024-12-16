package com.comic.android_native_client.network.services.impl

import com.comic.android_native_client.network.dto.request.ComicCategoryRequest
import com.comic.android_native_client.network.dto.response.ComicCategoryResponse
import com.comic.android_native_client.network.services.ComicCategoryService
import com.comic.android_native_client.network.services.ComicCategoryServiceAuthenticated
import com.comic.android_native_client.network.services.ComicCategoryServicePublic
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ComicCategoryServiceImpl @Inject constructor(
    private val publicClientRetrofit: Retrofit,
    private val authenticatedClientRetrofit: Retrofit
) : ComicCategoryService {

    private val publicComicCategoryService by lazy {
        publicClientRetrofit.create(ComicCategoryServicePublic::class.java)
    }

    private val authenticatedComicCategoryService by lazy {
        authenticatedClientRetrofit.create(ComicCategoryServiceAuthenticated::class.java)
    }

    override suspend fun fetchComicCategories(): Response<List<ComicCategoryResponse>> {
        return publicComicCategoryService.fetchComicCategories()
    }

    override suspend fun addComicCategory(newCategory: ComicCategoryRequest): Response<ComicCategoryResponse> {
        return authenticatedComicCategoryService.addComicCategory(newCategory)
    }
}