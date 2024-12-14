package com.comic.android_native_client.network.constants

object MainEndpoint {
    const val BASE_URL = "https://cda6-125-235-239-171.ngrok-free.app/api/"

    const val REFRESH_TOKEN_V1 = "v1/auth/refresh-token"

    const val LOGIN_V1 = "v1/auth/login"
    const val REGISTER_V1 = "v1/auth/register"
    const val LOGOUT_V1 = "v1/auth/logout"

    // USER
    const val GET_USER_PROFILE_V1 = "v1/users/profile"
    const val UPDATE_USER_PROFILE_V1 = "v1/users/profile"

    // FAVORITE
    const val GET_FAVORITE_V1 = "v1/comics/followed"
    const val GET_FAVORITE_COMIC_STATUS_V1 = "v1/comics/{comicId}/follow-status"
    const val ADD_FAVORITE_V1 = "v1/comics/followed/{comicId}"
    const val REMOVE_FAVORITE_V1 = "v1/comics/followed/{comicId}"

}


