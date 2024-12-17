package com.comic.android_native_client.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Sort(
    val sorted: Boolean = false,
    val unsorted: Boolean = true,
    val empty: Boolean = true
)

@Serializable
data class Pageable(
    val pageNumber: Int = 0,
    val pageSize: Int,
    val offset: Int,
    val paged: Boolean,
    val unpaged: Boolean,
    val sort: Sort
)

@Serializable
data class Page<T>(
    val content: List<T> = emptyList(),
    val size: Int,
    val sort: Sort,
    val totalPages: Int,
    val totalElements: Int,
    val number: Int,
    val numberOfElements: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean,
    val pageable: Pageable
) {
    override fun toString(): String {
        return "Page(content=$content,  size=$size, sort=$sort, totalPages=$totalPages, totalElements=$totalElements, number=$number, numberOfElements=$numberOfElements, first=$first, last=$last, empty=$empty,  pageable=$pageable)"
    }
}
