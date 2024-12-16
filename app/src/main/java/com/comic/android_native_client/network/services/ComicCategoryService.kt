package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.request.ComicCategoryRequest
import com.comic.android_native_client.network.dto.response.ComicCategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ComicCategoryServicePublic {
    @GET(MainEndpoint.GET_CATEGORIES_V1)
    suspend fun fetchComicCategories(): Response<List<ComicCategoryResponse>>
}

interface ComicCategoryServiceAuthenticated {
    @POST(MainEndpoint.ADD_NEW_CATEGORY_V1)
    suspend fun addComicCategory(
        @Body newCategory: ComicCategoryRequest
    ): Response<ComicCategoryResponse>
}

interface ComicCategoryService : ComicCategoryServicePublic, ComicCategoryServiceAuthenticated