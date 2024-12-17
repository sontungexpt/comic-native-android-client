package com.comic.android_native_client.data.model

import kotlinx.datetime.Instant

class ComicDetail(
    override val id: String,
    name: String,
    slug: String,
    status: String,
    originalSource: OriginalSource,
    newChapterUpdatedAt: Instant,
    alternativeNames: List<String> = emptyList(),
    thumbnailUrl: String = "",
    authors: List<Person> = emptyList(),
    artists: List<Person> = emptyList(),
    categories: List<ComicCategory> = emptyList(),
    tags: List<String> = emptyList(),
    newChapters: List<Chapter> = emptyList(),
    characters: List<Person> = emptyList(),

    val summary: String = "",
    val chapters: Page<Chapter>
) : Comic(
    id,
    name,
    slug,
    status,
    alternativeNames,
    thumbnailUrl,
    authors,
    artists,
    categories,
    tags,
    newChapters,
    originalSource,
    characters,
    newChapterUpdatedAt
)
