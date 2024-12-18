package com.comic.android_native_client.ui.activities.authentications.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.ui.globalState.SharedUserState
import com.comic.android_native_client.ui.globalState.UserState
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.ValidableTextFieldWatcher
import com.comic.validation_text_field.validator.RequiredValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedUserState: SharedUserState,
    private val authService: AuthService,
) : ViewModel() {
    private val requiredValidator = RequiredValidator()
    val usernameState = ValidableTextFieldState(
        fieldName = "Username",
        validators = listOf(requiredValidator),
    )
    val passwordState = ValidableTextFieldState(
        fieldName = "Password",
        validators = listOf(requiredValidator),
    )

    private val fieldWatcher = ValidableTextFieldWatcher(usernameState, passwordState)

    private var _loginProcessing = mutableStateOf(false)
    val loginProcessing: Boolean
        get() = _loginProcessing.value

    private val _error = MutableSharedFlow<String>(replay = 0) // SharedFlow for error messages
    val error: SharedFlow<String> = _error

    fun validateFields(): Boolean {
        return fieldWatcher.validateAll()
    }

    fun login(navigateToHome: () -> Unit) {
        if (validateFields()) {
            _loginProcessing.value = true
            viewModelScope.launch {
                try {
                    val response = authService.login(
                        LoginRequest(
                            username = usernameState.value,
                            password = passwordState.value
                        )
                    )
                    if (response.isSuccessful) {
                        response.body()?.let {
                            sharedUserState.setUser(
                                UserState(
                                    name = it.name,
                                    avatar = it.avatar
                                )
                            )
                            _error.emit("")
                            navigateToHome()
                        }


                    } else {
                        val errorMessage = when (HttpStatus.from(response.code())) {
                            HttpStatus.NotFound -> "User not found"
                            else -> "Something went wrong"
                        }
                        _error.emit(errorMessage)
                    }
                } catch (e: Exception) {
                    _error.emit(e.message ?: "Something went wrong")
                }
                _loginProcessing.value = false

            }

        }
    }
}
