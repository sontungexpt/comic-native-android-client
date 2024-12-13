package com.comic.android_native_client.network.services.auth

import com.comic.android_native_client.data.repository.JwtRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val jwtRepository: JwtRepository,
    private val refreshTokenService: RefreshTokenService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            jwtRepository.getAccessJwt()
        }
        synchronized(this) {
            val updatedToken = runBlocking {
                jwtRepository.getAccessJwt()
            }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking {
                    refreshTokenService.refreshToken()
                }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let { body ->
                        runBlocking {
                            jwtRepository.saveAccessJwt(body.accessToken)
                            jwtRepository.saveRefreshJwt(body.refreshToken)
                        }
                        body.accessToken
                    }
                } else null
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$BEARER_TOKEN_TYPE $token")
                .build() else null
        }
    }
}