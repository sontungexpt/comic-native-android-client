package com.comic.android_native_client.network.constants

object MainEndpoint {
    const val BASE_URL = "https://fd92-123-22-56-236.ngrok-free.app/api/"

    const val REFRESH_TOKEN_V1 = "v1/auth/refresh-token"

    const val LOGIN_V1 = "v1/auth/login"
    const val REGISTER_V1 = "v1/auth/register"
    const val LOGOUT_V1 = "v1/auth/logout"

    // USER
    const val GET_USER_PROFILE_V1 = "v1/users/profile"
    const val UPDATE_USER_PROFILE_V1 = "v1/users/profile"

    // FAVORITE
    const val GET_FAVORITE_COMICS_V1 = "v1/comics/followed"
    const val GET_FAVORITE_COMIC_STATUS_V1 = "v1/comics/{comicId}/follow-status"
    const val ADD_FAVORITE_COMIC_V1 = "v1/comics/followed/{comicId}"
    const val REMOVE_FAVORITE_COMIC_V1 = "v1/comics/followed/{comicId}"

    // Comic
    const val SEARCH_COMICS_V1 = "v1/comics/searching"
    const val GET_COMICS_V1 = "v1/comics"
    const val ADD_COMIC_V1 = "v1/comics"
    const val GET_COMIC_DETAIL_V1 = "v1/comics/{comicId}"


    // Category
    const val GET_CATEGORIES_V1 = "v1/categories"
    const val ADD_NEW_CATEGORY_V1 = "v1/categories"
    const val ADD_NEW_CATERGORY_BATCH_V1 = "v1/categories/bulk"

    // Comment
    const val ADD_COMMENT_V1 = "v1/comments"
    const val GET_TOP_LEVEL_COMMENTS_V1 = "v1/comments/top-level"
    const val GET_TOP_LEVEL_REPLIES_V1 = "v1/comments/{commentId}/replies/top-level"

    // Chapter
    const val GET_ALL_CHAPTERS_V1 = "v1/comics/{comicId}/chapters"
    const val GET_CHAPTER_DETAIL_V1 = "v1/comics/{comicId}/chapters/{chapterId}"


}


