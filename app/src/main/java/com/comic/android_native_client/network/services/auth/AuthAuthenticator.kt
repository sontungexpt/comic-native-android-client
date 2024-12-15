package com.comic.android_native_client.network.services.auth

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val jwtRepository: JwtRepository,

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
                        body.accessToken
                    }
                } else null
            }

            return if (newAccessToken != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$BEARER_TOKEN_TYPE $newAccessToken")
                .build()
            else null
        }
    }
}