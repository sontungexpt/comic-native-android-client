package com.comic.android_native_client.ui.globalState

import androidx.compose.runtime.mutableStateOf
import com.comic.android_native_client.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

const val MAX_IMAGE_ID = 6
val RANDOM_AVATAR = when ((1..MAX_IMAGE_ID).random()) {
    1 -> R.drawable.default_avatar_1
    2 -> R.drawable.default_avatar_2
    3 -> R.drawable.default_avatar_3
    4 -> R.drawable.default_avatar_4
    5 -> R.drawable.default_avatar_5
    6 -> R.drawable.default_avatar_6
    else -> R.drawable.default_avatar_1
}

data class UserState(
    val name: String,
    val avatar: String,
)

@Singleton
class SharedUserState @Inject constructor() {
    private val _userState = MutableStateFlow(UserState(name = "", avatar = ""))
    val userState = _userState.asStateFlow()

    private val _loggedIn = mutableStateOf(false)
    val loggedIn: Boolean get() = _loggedIn.value

    fun setUser(userState: UserState) {
        _userState.value = userState
        _loggedIn.value = true
    }

    fun clearUser() {
        _userState.value = UserState(name = "", avatar = "")
        _loggedIn.value = false
    }
}

