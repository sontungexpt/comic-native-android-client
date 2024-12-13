package com.comic.android_native_client.di.module.services

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.di.qualifiers.PublicClient
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.network.services.impl.AuthServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceConfig {

    @Provides
    @Singleton
    fun providesAuthService(
        @TokenRefreshClient
        tokenRefreshClientRetrofit: Retrofit,
        @PublicClient
        publicClientRetrofit: Retrofit,
        jwtRepository: JwtRepository
    ): AuthService {
        return AuthServiceImpl(
            refreshTokenClientRetrofit = tokenRefreshClientRetrofit,
            publicClientRetrofit = publicClientRetrofit,
            jwtRpository = jwtRepository
        )
    }
}