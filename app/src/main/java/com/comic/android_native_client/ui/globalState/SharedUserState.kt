package com.comic.android_native_client.ui.globalState

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.dto.response.LoginResponse
import com.comic.android_native_client.network.services.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

data class UserState(
    val name: String,
    val avatar: String,
)

@Singleton
class SharedUserState @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authService: AuthService
) {
    private val sharedUserStateScope = CoroutineScope(Dispatchers.IO)
    private val _userState = MutableStateFlow(
        UserState(
            name = "",
            avatar = ""
        )
    )
    val userState = _userState.asStateFlow()
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn
        get() = _isLoggedIn.value
    private var _loginProcess = mutableStateOf(false)
    val loginProcessing
        get() = _loginProcess.value

    fun login(
        userName: String, password: String,
        sucessHalder: (response: Response<LoginResponse>) -> Boolean,
        errorHalder: (status: HttpStatus, response: Response<LoginResponse>) -> Boolean
    ) {
        _loginProcess.value = true
        sharedUserStateScope.launch {
            val response = authService.login(
                LoginRequest(
                    username = userName,
                    password = password
                )
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    _userState.value = UserState(
                        name = it.name,
                        avatar = ""
                    )
                }
                _isLoggedIn.value = true
                sucessHalder(response)
            } else {
                if (!errorHalder(HttpStatus.from(response.code()), response)) {
                    when (HttpStatus.from(response.code())) {
                        HttpStatus.NotFound -> {
                            Toast.makeText(
                                context,
                                "User not found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
            _loginProcess.value = false
        }

    }

    fun setUser(userState: UserState) {
        _userState.value = userState
    }

    fun clearUser() {
        _userState.value = UserState(
            name = "",
            avatar = ""
        )
    }

    fun getUser(): UserState {
        return _userState.value
    }
}

