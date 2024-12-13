package com.comic.android_native_client.network.services.auth

import com.comic.android_native_client.data.repository.JwtRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val jwtRepository: JwtRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            jwtRepository.getAccessJwt()
        }
        val request = chain.request().newBuilder()
        request.addHeader(HEADER_AUTHORIZATION, "$BEARER_TOKEN_TYPE $token")
        return chain.proceed(request.build())
    }
}