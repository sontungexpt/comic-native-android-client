package com.comic.android_native_client.network.services.auth

import android.util.Log
import com.comic.android_native_client.constants.HttpStatus
import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import com.comic.android_native_client.ui.globalState.SharedUserState
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import javax.inject.Inject

const val TAG = "AuthAuthenticator"

class AuthAuthenticator @Inject constructor(
    private val jwtRepository: JwtRepository,
    private val sharedUserState: SharedUserState,
    @TokenRefreshClient
    private val refrshTokenClientRetrofit: Retrofit,
) : Authenticator {

    private val refreshTokenService by lazy {
        refrshTokenClientRetrofit.create(RefreshTokenService::class.java)
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            jwtRepository.getAccessJwt()
        }
        synchronized(this) {
            Log.d(TAG, "Refreshing access token")
            val updatedToken = runBlocking {
                jwtRepository.getAccessJwt()
            }
            val newAccessToken = if (currentToken != updatedToken)
                updatedToken
            else {
                val refreshTokenResponse = runBlocking {
                    refreshTokenService.refreshToken()
                }

                if (refreshTokenResponse.isSuccessful) {
                    refreshTokenResponse.body()?.let { body ->
                        runBlocking {
                            jwtRepository.saveAccessJwt(body.accessToken)
                            jwtRepository.saveRefreshJwt(body.refreshToken)
                        }
                        Log.d(TAG, "Access token refreshed")
                        body.accessToken
                    }

                } else {
                    val status = HttpStatus.from(refreshTokenResponse.code())
                    if (status == HttpStatus.Unauthorized) {
                        runBlocking {
                            sharedUserState.clearUser()
                            jwtRepository.clearAllTokens()
                            Log.d(TAG, "Refresh token expired")
                        }
                    }
                    null
                }
            }

            return if (newAccessToken != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$BEARER_TOKEN_TYPE $newAccessToken")
                .build()
            else null
        }
    }
}