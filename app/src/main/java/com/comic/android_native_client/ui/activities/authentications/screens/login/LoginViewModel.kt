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
    private val authService: AuthService,
    private val sharedUserState: SharedUserState,
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
                        sharedUserState.setUser(
                            UserState(
                                name = usernameState.value,
                                avatar = ""
                            )
                        )
                        _error.emit("")
                        navigateToHome()
//                        context.startActivity(
//                            Intent(context, AppActivity::class.java)
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                        )

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


//    fun login(context: Context) {
//        if (validateFields()) {
//            sharedUserState.login(
//                usernameState.value,
//                passwordState.value,
//                {
//                    context.startActivity(
//                        Intent(context, AppActivity::class.java)
//                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    )
//
//                    false
//                },
//                { status, response ->
//                    false
//                }
//            )
//
//
//        }
//    }
}
