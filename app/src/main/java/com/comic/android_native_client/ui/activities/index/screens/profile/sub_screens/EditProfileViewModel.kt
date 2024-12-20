package com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.ui.globalState.SharedUserState
import com.comic.android_native_client.ui.globalState.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val sharedUserState: SharedUserState
) : ViewModel() {
    private var _name by mutableStateOf(sharedUserState.userState.value.name)
    private var _avatar by mutableStateOf(sharedUserState.userState.value.avatar)
    private var _introduction by mutableStateOf(sharedUserState.userState.value.introduction)
    private var _loading by mutableStateOf(false)

    val name: String
        get() = _name

    fun updateName(value: String) {
        _name = value
    }

    val avatar: String
        get() = _avatar

    fun updateAvatar(value: String) {
        _avatar = value
    }

    val introduction: String
        get() = _introduction

    fun updateIntroduction(value: String) {
        _introduction = value
    }

    val loading: Boolean
        get() = _loading

    fun updateProfile(navigateBack: () -> Unit) {
        _loading = true
        viewModelScope.launch(Dispatchers.IO) {

            sharedUserState.setUser(
                UserState(
                    name = _name,
                    avatar = _avatar,
                    introduction = _introduction
                )
            )
            withContext(Dispatchers.Main) {
                navigateBack()
            }
            _loading = false
        }


    }


}

