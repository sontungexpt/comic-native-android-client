package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.ui.globalState.SharedUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val sharedUserState: SharedUserState,
    private val jwtRepository: JwtRepository
) : ViewModel() {
    val userState
        get() = sharedUserState.userState
    val isLoggedIn
        get() = sharedUserState.loggedIn

    fun logout(navigateToHome: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = authService.logout()
                if (response.isSuccessful) {
                    jwtRepository.clearAllTokens()
                    sharedUserState.clearUser()
                    navigateToHome()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}