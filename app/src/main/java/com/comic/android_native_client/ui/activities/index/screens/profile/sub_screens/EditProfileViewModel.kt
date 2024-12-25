package com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.Result
import com.comic.android_native_client.data.repository.UserRepository
import com.comic.android_native_client.network.dto.request.UpdateUserInfoRequest
import com.comic.android_native_client.ui.globalState.SharedUserState
import com.comic.android_native_client.ui.globalState.UserState
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.ValidableTextFieldWatcher
import com.comic.validation_text_field.validator.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedUserState: SharedUserState
) : ViewModel() {
    private var _emailState = ValidableTextFieldState(
        value = "",
        fieldName = "Email",
        validators = listOf(EmailValidator()),
    )
    private var stateWatcher = ValidableTextFieldWatcher(_emailState)

    private var _name by mutableStateOf(sharedUserState.userState.value.name)
    private var _avatar by mutableStateOf(sharedUserState.userState.value.avatar)
    private var _introduction by mutableStateOf(sharedUserState.userState.value.bio)
    private var _loading by mutableStateOf(false)

    val name: String
        get() = _name

    fun updateName(value: String) {
        _name = value
    }

    fun updateEmail(value: String) {
        _emailState.value = value
    }

    val emailState: ValidableTextFieldState
        get() = _emailState
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
        if (!stateWatcher.validateAll()) {
            return
        }
        _loading = true
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val result = userRepository.updateUser(
                    UpdateUserInfoRequest(
                        name = _name,
                        email = emailState.value,
                        bio = _introduction
                    )
                )
                when (result) {
                    is Result.Success -> {
                        sharedUserState.setUser(
                            UserState(
                                name = _name,
                                avatar = _avatar,
                                email = _emailState.value,
                                bio = _introduction
                            )
                        )
                        withContext(Dispatchers.Main) {
                            navigateBack()
                        }
                        _loading = false
                    }

                    is Result.Error -> {
                        // Handle error
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }


}

