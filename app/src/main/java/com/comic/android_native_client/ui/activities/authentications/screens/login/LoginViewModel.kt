package com.comic.android_native_client.ui.activities.authentications.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.services.AuthService
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.ValidableTextFieldWatcher
import com.comic.validation_text_field.validator.LengthValidator
import com.comic.validation_text_field.validator.PasswordValidator
import com.comic.validation_text_field.validator.RequiredValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    val usernameState = ValidableTextFieldState(
        fieldName = "Username",
        validators = listOf(RequiredValidator(), LengthValidator(2, 50)),
    )
    val passwordState = ValidableTextFieldState(
        fieldName = "Password",
        validators = listOf(RequiredValidator(), PasswordValidator()),
    )

    private val fieldWatcher = ValidableTextFieldWatcher(usernameState, passwordState)

    private var _isLoading = mutableStateOf(false)
    val isLoading: Boolean
        get() = _isLoading.value

    private var _error = mutableStateOf("")
    val error: String
        get() = _error.value

    fun validateFields(): Boolean {
        return fieldWatcher.validateAll()
    }

    fun login(navController: NavController) {
        if (validateFields()) {
            _isLoading.value = true
            viewModelScope.launch {
                val response = authService.login(
                    LoginRequest(
                        username = usernameState.value,
                        password = passwordState.value
                    )
                )
                if (response.isSuccessful) {
                    println(response.body()?.jwt.toString())

                }
                _isLoading.value = false
            }

        }
    }
}
