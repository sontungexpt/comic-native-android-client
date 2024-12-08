package com.comic.android_native_client.exmaple.data

import com.comic.android_native_client.common.Identifiable

data class Genre(
    override
    val id: String,
    val name: String,
) : Identifiable<String>

val genres = listOf(
    Genre(
        id = "1",
        name = "Action",
    ),
    Genre(
        id = "2",
        name = "Anime"
    ),
    Genre(
        id = "3",
        name = "Reincarnation"
    ),
    Genre(
        id = "4",
        name = "Ancient"
    ),
    Genre(
        id = "5",
        name = "Adventure"
    ),
    Genre(
        id = "6",
        name = "Comedy"
    ),
    Genre(
        id = "7",
        name = "Drama"
    ),
    Genre(
        id = "8",
        name = "Fantasy"
    ),
    Genre(
        id = "9",
        name = "Historical"
    ),
    Genre(
        id = "10",
        name = "Horror"
    ),
    Genre(
        id = "11",
        name = "Mystery"
    ),
    Genre(
        id = "12",
        name = "Psychological"
    ),
    Genre(
        id = "13",
        name = "Romance"
    ),
    Genre(
        id = "14",
        name = "School"
    ),
)