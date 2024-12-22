package com.comic.android_native_client.network.dto.response

import com.comic.android_native_client.data.model.Chapter
import com.comic.android_native_client.data.model.ComicChapter
import com.comic.android_native_client.data.model.ImagePage
import com.comic.android_native_client.data.model.NovelChapter
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)

//object ChapterResponseSerializer :
//    JsonContentPolymorphicSerializer<ChapterResponse>(ChapterResponse::class) {
//    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ChapterResponse> {
//        val jsonObject = element.jsonObject
//
//        // Xác định lớp con dựa trên các trường JSON cụ thể
//        return when {
//            "imagePages" in jsonObject -> ComicChapterResponse.serializer()
//            "content" in jsonObject -> NovelChapterResponse.serializer()
//            else -> throw IllegalArgumentException("Unknown ChapterResponse type")
//        }
//    }
//}

@Polymorphic
abstract class ChapterResponse {
    abstract val id: String
    abstract val comicId: String
    abstract val thumbnailUrl: String
    abstract val num: Double
    abstract val name: String
    abstract val description: String
    abstract val originalSource: OriginalSourceResponse
    abstract val updatedDate: Instant?
    abstract val chapter: String
}

fun ChapterResponse.toChapter(): Chapter {
    return when (this) {
        is ShortInfoChapterResponse -> toChapter()
        is ComicChapterResponse -> toChapter()
        is NovelChapterResponse -> toChapter()
        else -> {
            throw IllegalArgumentException("Unknown chapter type")
        }
    }
}

@Serializable
class ShortInfoChapterResponse(
    override val id: String,
    override val comicId: String,
    override val thumbnailUrl: String = "",
    override val num: Double,
    override val name: String,
    override val description: String = "",
    override val originalSource: OriginalSourceResponse,
    override val updatedDate: Instant?,
    override val chapter: String,
    val type: String
) : ChapterResponse()

fun ShortInfoChapterResponse.toChapter(): Chapter {
    return when (type) {
        "COMIC" -> ComicChapter(
            id = id,
            comicId = comicId,
            type = type,
            thumbnailUrl = thumbnailUrl,
            num = num,
            name = name,
            description = description,
            originalSource = originalSource.toOriginalSource(),
            updatedDate = updatedDate,
            chapter = chapter,
            imagePages = emptyList(),
            resourceInfo = ResourceInfo.Absolute
        )

        "NOVEL" -> NovelChapter(
            id = id,
            comicId = comicId,
            type = type,
            thumbnailUrl = thumbnailUrl,
            num = num,
            name = name,
            description = description,
            chapter = chapter,
            originalSource = originalSource.toOriginalSource(),
            updatedDate = updatedDate,
            content = ""
        )

        else -> throw IllegalArgumentException("Unknown chapter type: $type")
    }
}

@Serializable
@Polymorphic
sealed class ResourceInfoResponse {

    @Serializable
    @SerialName("ABSOLUTE")
    object Absolute : ResourceInfoResponse()

    @Serializable
    @SerialName("RELATIVE")
    data class Relative(
        val baseUrl: String
    ) : ResourceInfoResponse()
}

fun ResourceInfoResponse.toResourceInfo(): ResourceInfo {
    return when (this) {
        is ResourceInfoResponse.Absolute -> ResourceInfo.Absolute
        is ResourceInfoResponse.Relative -> ResourceInfo.Relative(baseUrl = baseUrl)
    }
}

@Serializable
data class ImagePageResponse(
    val number: Int,
    val path: String,
    val imageFileName: String
)

fun ImagePageResponse.toImagePage(): ImagePage {
    return ImagePage(
        number = number,
        path = path,
        imageFileName = imageFileName
    )
}

@Serializable
@SerialName("COMIC")
data class ComicChapterResponse(
    override val id: String,
    override val comicId: String,
    override val thumbnailUrl: String,
    override val num: Double,
    override val name: String,
    override val description: String,
    override val originalSource: OriginalSourceResponse,
    override val updatedDate: Instant?,
    override val chapter: String,
    val imagePages: List<ImagePageResponse>,
    @Polymorphic
    val resourceInfo: ResourceInfoResponse,
) : ChapterResponse()

fun ComicChapterResponse.toChapter(): ComicChapter {
    return ComicChapter(
        id = id,
        comicId = comicId,
        thumbnailUrl = thumbnailUrl,
        num = num,
        chapter = chapter,
        type = "COMIC",
        name = name,
        description = description,
        originalSource = originalSource.toOriginalSource(),
        updatedDate = updatedDate,
        imagePages = imagePages.map { it.toImagePage() },
        resourceInfo = resourceInfo.toResourceInfo()
    )
}

@Serializable
@SerialName("NOVEL")
class NovelChapterResponse(
    override val id: String,
    override val comicId: String,
    override val thumbnailUrl: String,
    override val num: Double,
    override val name: String,
    override val description: String,
    override val originalSource: OriginalSourceResponse,
    override val updatedDate: Instant?,
    override val chapter: String,
    val content: String,
) : ChapterResponse()

fun NovelChapterResponse.toChapter(): NovelChapter {
    return NovelChapter(
        id = id,
        comicId = comicId,
        thumbnailUrl = thumbnailUrl,
        num = num,
        chapter = chapter,
        type = "NOVEL",
        name = name,
        description = description,
        originalSource = originalSource.toOriginalSource(),
        updatedDate = updatedDate,
        content = content
    )
}