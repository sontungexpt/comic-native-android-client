package com.comic.android_native_client.exmaple.data

import com.comic.android_native_client.common.Identifiable

data class Comic(
    override
    val id: String,
    val authors: List<String>,
    val imageUrl: String,
    val name: String,
    val description: String,
    val rating: UInt,
    val newChapters: List<String>
) : Identifiable<String>

val comics = listOf(
    Comic(
        id = "1",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "2",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "3",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "4",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "5",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ),
    Comic(
        id = "6",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    ), Comic(
        id = "7",
        authors = listOf("Author"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
        name = "Nam chinh muon ly hon nhung vo anh khong chiu",
        description = "Comic description",
        rating = 5u,
        newChapters = listOf()

    )
)
