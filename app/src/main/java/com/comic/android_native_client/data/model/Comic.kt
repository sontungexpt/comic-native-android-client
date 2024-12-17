package com.comic.android_native_client.data.model

import com.comic.android_native_client.common.Identifiable
import kotlinx.datetime.Instant

open class Comic(
    override val id: String,
    val name: String,
    val slug: String,
    val status: String,
    val alternativeNames: List<String> = emptyList(),
    val thumbnailUrl: String = "",
    val authors: List<Person> = emptyList(),
    val artists: List<Person> = emptyList(),
    val categories: List<ComicCategory> = emptyList(),
    val tags: List<String> = emptyList(),
    val newChapters: List<Chapter> = emptyList(),
    val originalSource: OriginalSource,
    val characters: List<Person> = emptyList(),
    val newChapterUpdatedAt: Instant
) : Identifiable<String> {

    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        return if (this === other) true
        else if (other is Comic) id == other.id
        else false
    }
}







