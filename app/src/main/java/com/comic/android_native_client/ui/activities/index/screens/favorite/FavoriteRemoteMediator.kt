package com.comic.android_native_client.ui.activities.index.screens.favorite

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.comic.android_native_client.network.dto.response.ComicResponse

@OptIn(ExperimentalPagingApi::class)
class FavoriteRemoteMediator(
    private val query: String,
//    private val database: RoomDb,
//    private val networkService: ExampleBackendService
) : RemoteMediator<String, ComicResponse>() {
//    val userDao = database.userDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<String, ComicResponse>
    ): MediatorResult {

        return MediatorResult.Success(endOfPaginationReached = false)
    }
}