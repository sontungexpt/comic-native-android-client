package com.comic.android_native_client.network.services

import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.dto.response.ComicResponse
import retrofit2.Response
import retrofit2.http.GET

interface ComicServicePublic {
    @GET(MainEndpoint.GET_COMICS_V1)
    suspend fun getComic(): Response<PageResponse<ComicResponse>>
}