package com.comic.android_native_client.network.services

import kotlinx.serialization.Serializable

@Serializable
class PageResponse<T> {
    var content: List<T> = emptyList()
    var page: Int = 0
    var total: Int = 0
    var limit: Int = 0
    var offset: Int = 0
    var count: Int = 0
    var totalPage: Int = 0
    var nextPage: String? = null
    var previousPage: String? = null
}
