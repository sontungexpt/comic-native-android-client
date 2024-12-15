package com.comic.android_native_client.data.model

import com.comic.android_native_client.ui.components.common.SliceableItem

data class Comic(
    override val id: String = "3",
    val authors: List<String> = listOf("Author"),
    val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png",
    val name: String = "Nam chinh muon ly hon nhung vo anh khong chiu",
    val description: String = "Comic description",
    val rating: UInt = 5u,
    val newChapters: List<Chapter> = listOf()
) : SliceableItem<String>

//data class Comic(
//    override val id: String,
//    val imageUrl: String,
//    val name: String,
//    val alternativeNames: List<String> = emptyList(),
//    val summary: String = "",
//    val thumbnailUrl: String,
//    val slug: String,
//    val description: String = "",
//    val rating: UInt,
//    val status: String,
//    val dailyViews: Int,
//    val weeklyViews: Int,
//    val monthlyViews: Int,
//    val yearlyViews: Int,
//    val newChapters: List<Chapter>,
//    val authors: List<String> = emptyList(),
//    val artists: List<String> = emptyList(),
//    val categoryIds: List<String> = emptyList(),
//    val tags: List<String> = emptyList(),
//    val lastNewChapterCheckAt: Instant,
//    val newChapterUpdatedAt: Instant,
//    val ownerId: String,
//) : SliceableItem<String>


//
//
//@Schema(
//    description = "The original source of the comic",
//    example =
//    "{\"name\":\"MangaDex\",\"description\":\"MangaDex\", \"link\":\"https://mangadex.org\"}",
//    requiredMode = RequiredMode.NOT_REQUIRED)
//private OriginalSource originalSource;


//@JsonGetter("newChapters")
//@Schema(hidden = true)
//public List<ShortInfoChapter> getNewChaptersInfo() {
//    if (newChapters == null) return List.of();
//    return newChapters.stream().sorted((c1, c2) -> c2.getNum().compareTo(c1.getNum())).toList();
//}


//
//@Schema(
//    description = "The characters in the comic",
//    type = "array",
//    exampleClasses = {Character.class})
//private List<@Valid Character> characters;
//
//@Schema(
//    description = "The translators of the comic",
//    type = "array",
//    exampleClasses = {Translator.class})
//private List<@Valid Translator> translators;
//





