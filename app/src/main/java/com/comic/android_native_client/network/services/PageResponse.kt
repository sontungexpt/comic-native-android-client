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
    val hasPrevious: Boolean,
    val sort: Sort
)

fun PageableResponse.toPageable() =
    Pageable(pageNumber, pageSize, offset, paged, unpaged, hasPrevious, sort)

@Serializable
data class PageResponse<T>(
    val content: List<T> = emptyList(),
    val hasContent: Boolean,
    val size: Int,
    val sort: SortResponse,
    val totalPages: Int,
    val totalElements: Int,
    val number: Int,
    val numberOfElements: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean,
    val sorted: Boolean,
    val pageable: PageableResponse
)

fun <T, D : Any> PageResponse<T>.toPage(map: (T) -> D): Page<D> =
    Page<D>(
        content = content.map(map),
        hasContent = hasContent,
        size = size,
        sort = sort.toSort(),
        totalPages = totalPages,
        totalElements = totalElements,
        number = number,
        numberOfElements = numberOfElements,
        first = first,
        last = last,
        empty = empty,
        sorted = sorted,
        pageable = pageable.toPageable()
    )
