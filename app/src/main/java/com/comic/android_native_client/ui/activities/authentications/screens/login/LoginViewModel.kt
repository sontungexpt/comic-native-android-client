package com.comic.android_native_client.ui.activities.authentications.screens.login


import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.ui.activities.index.AppActivity
import com.comic.android_native_client.ui.globalState.SharedUserState
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.ValidableTextFieldWatcher
import com.comic.validation_text_field.validator.LengthValidator
import com.comic.validation_text_field.validator.RequiredValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val sharedUserState: SharedUserState,
) : ViewModel() {
    val usernameState = ValidableTextFieldState(
        fieldName = "Username",
        validators = listOf(RequiredValidator(), LengthValidator(2, 50)),
    )
    val passwordState = ValidableTextFieldState(
        fieldName = "Password",
        validators = listOf(RequiredValidator()),
    )

    private val fieldWatcher = ValidableTextFieldWatcher(usernameState, passwordState)

    private var _isLoading = mutableStateOf(false)
    val loginProcessing: Boolean
        get() = sharedUserState.loginProcessing

    private var _error = mutableStateOf("")
    val error: String
        get() = _error.value

    fun validateFields(): Boolean {
        return fieldWatcher.validateAll()
    }

    //    fun login(navController: NavController) {
//        if (validateFields()) {
//            _isLoading.value = true
//            viewModelScope.launch {
//                val response = authService.login(
//                    LoginRequest(
//                        username = usernameState.value,
//                        password = passwordState.value
//                    )
//                )
//                if (response.isSuccessful) {
//                    sharedUserState.setUser(
//                        UserState(
//                            name = usernameState.value,
//                            avatar = ""
//                        )
//                    )
//                    sharedUserState.isLoggedIn = true
//                    context.startActivity(
//                        Intent(context, AppActivity::class.java)
//                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    )
//                } else {
//                    when (HttpStatus.from(response.code())) {
//                        HttpStatus.NotFound -> {
//                            _error.value = "User not found"
//                            Toast.makeText(
//                                navController.context,
//                                "User not found",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                        else -> {
//                            _error.value = "Something went wrong"
//
//                            Toast.makeText(
//                                navController.context,
//                                "Something went wrong",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//                _isLoading.value = false
//            }
//
//        }
//    }
    fun login(context: Context) {
        if (validateFields()) {
            sharedUserState.login(
                usernameState.value,
                passwordState.value,
                {
                    context.startActivity(
                        Intent(context, AppActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )

                    false
                },
                { status, response ->
                    false
                }
            )


        }
    }
}
