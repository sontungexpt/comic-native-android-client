package com.comic.android_native_client.ui.globalState

import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.network.dto.request.LoginRequest
import com.comic.android_native_client.network.dto.response.LoginResponse
import com.comic.android_native_client.network.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationManager @Inject constructor(
    private val authService: AuthService
) {
    private val sharedUserStateScope = CoroutineScope(Dispatchers.IO)

    fun login(
        userName: String,
        password: String,
        onSuccess: (LoginResponse, Response<LoginResponse>) -> Unit,
        onError: (HttpStatus, Response<LoginResponse>) -> Unit
    ) {
        sharedUserStateScope.launch {
            val response = authService.login(
                LoginRequest(
                    username = userName,
                    password = password
                )
            )

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    onSuccess(body, response)
                }
            } else {
                onError(HttpStatus.from(response.code()), response)
            }
        }
    }
}
