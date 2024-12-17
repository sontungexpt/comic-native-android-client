package com.comic.android_native_client.network.services

import com.comic.android_native_client.data.model.Page
import com.comic.android_native_client.data.model.Pageable
import com.comic.android_native_client.data.model.Sort
import kotlinx.serialization.Serializable

@Serializable
data class SortResponse(
    val sorted: Boolean = false,
    val unsorted: Boolean = true,
    val empty: Boolean = true
)

fun SortResponse.toSort() = Sort(sorted, unsorted, empty)

@Serializable
data class PageableResponse(
    val pageNumber: Int = 0,
    val pageSize: Int,
    val offset: Int,
    val paged: Boolean,
    val unpaged: Boolean,
    val sort: Sort
)

fun PageableResponse.toPageable() =
    Pageable(pageNumber, pageSize, offset, paged, unpaged, sort)

@Serializable
data class PageResponse<T>(
    val content: List<T> = emptyList(),
    val totalElements: Int,
    val totalPages: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: SortResponse,
    val first: Boolean,
    val numberOfElements: Int,
    val empty: Boolean,
    val pageable: PageableResponse
)

fun <T, D : Any> PageResponse<T>.toPage(map: (T) -> D): Page<D> =
    Page(
        content = content.map(map),
        size = size,
        sort = sort.toSort(),
        totalPages = totalPages,
        totalElements = totalElements,
        number = number,
        numberOfElements = numberOfElements,
        first = first,
        last = last,
        empty = empty,
        pageable = pageable.toPageable()
    )
