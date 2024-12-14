package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.lifecycle.ViewModel
import com.comic.android_native_client.ui.globalState.SharedUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedUserState: SharedUserState
) : ViewModel() {
    val userState
        get() = sharedUserState.userState
    val isLoggedIn
        get() = sharedUserState.loggedIn
}