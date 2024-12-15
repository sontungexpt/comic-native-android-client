package com.comic.android_native_client.di.module.services

import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.di.qualifiers.AuthenticatedClient
import com.comic.android_native_client.di.qualifiers.PublicClient
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import com.comic.android_native_client.network.services.AuthService
import com.comic.android_native_client.network.services.ComicCategoryService
import com.comic.android_native_client.network.services.FavoriteComicService
import com.comic.android_native_client.network.services.UserService
import com.comic.android_native_client.network.services.impl.AuthServiceImpl
import com.comic.android_native_client.network.services.impl.ComicCategoryServiceImpl
import com.comic.android_native_client.network.services.impl.FavoriteComicServiceImpl
import com.comic.android_native_client.network.services.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceConfig {

    @[Provides Singleton]
    fun provideAuthService(
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

    @[Provides Singleton]
    fun provideUserService(
        @AuthenticatedClient
        authenticatedRetrofit: Retrofit,
        jwtRepository: JwtRepository
    ): UserService {
        return UserServiceImpl(
            authenticatedRetrofit = authenticatedRetrofit,
            jwtRepository = jwtRepository
        )
    }

    @[Provides Singleton]
    fun provideComicCategoryService(
        @PublicClient
        publicClientRetrofit: Retrofit,
        @AuthenticatedClient
        authenticatedRetrofit: Retrofit
    ): ComicCategoryService {
        return ComicCategoryServiceImpl(
            publicClientRetrofit = publicClientRetrofit,
            authenticatedClientRetrofit = authenticatedRetrofit
        )
    }

    @[Provides Singleton]
    fun provideFavoriteComicService(
        @AuthenticatedClient
        authenticatedRetrofit: Retrofit
    ): FavoriteComicService {
        return FavoriteComicServiceImpl(
            authenticatedRetrofit = authenticatedRetrofit
        )
    }
}