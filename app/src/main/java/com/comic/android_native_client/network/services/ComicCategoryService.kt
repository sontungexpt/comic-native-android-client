package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.ComicCatergoriesResponse
import com.comic.android_native_client.network.dto.response.ComicCatergoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ComicCategoryServicePublic {
    @GET(MainEndpoint.GET_CATEGORIES_V1)
    suspend fun fetchComicCategories(): Response<ComicCatergoriesResponse>
}

interface ComicCategoryServiceAuthenticated {
    @POST(MainEndpoint.ADD_NEW_CATEGORY_V1)
    suspend fun addComicCategory(
        @Body newCategory: ComicCatergoryRequest
    ): ComicCatergoryResponse
}

interface ComicCategoryService : ComicCategoryServicePublic, ComicCategoryServiceAuthenticated