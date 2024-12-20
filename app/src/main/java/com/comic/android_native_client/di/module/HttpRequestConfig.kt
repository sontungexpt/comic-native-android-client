package com.comic.android_native_client.di.module

import com.comic.android_native_client.di.qualifiers.AuthenticatedClient
import com.comic.android_native_client.di.qualifiers.PublicClient
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.services.auth.AccessTokenInterceptor
import com.comic.android_native_client.network.services.auth.AuthAuthenticator
import com.comic.android_native_client.network.services.auth.OptionalAccessTokenInterceptor
import com.comic.android_native_client.network.services.auth.RefreshTokenInterceptor
import com.comic.android_native_client.serialization.module
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpRequestConfig {

    private val networkJson = Json {
        serializersModule = module
        classDiscriminator = "type"
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    private val factory = networkJson.asConverterFactory(
        "application/json; charset=UTF8".toMediaType()
    )
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @[Provides Singleton AuthenticatedClient]
    fun provideAccessOkHttpClient(
        accessTokenInterceptor: AccessTokenInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(accessTokenInterceptor)
        .authenticator(authAuthenticator)
        .build()

    @[Provides Singleton TokenRefreshClient]
    fun provideRefreshOkHttpClient(
        refreshTokenInterceptor: RefreshTokenInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(refreshTokenInterceptor)
        .build()

    @[Provides Singleton PublicClient]
    fun provideUnauthenticatedOkHttpClient(
        authAuthenticator: AuthAuthenticator,
        optionalAccessTokenInterceptor: OptionalAccessTokenInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(optionalAccessTokenInterceptor)
        .authenticator(authAuthenticator)
        .build()


    @[Provides Singleton AuthenticatedClient]
    fun provideAuthenticatedRetrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MainEndpoint.BASE_URL)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
    }

    @[Provides Singleton TokenRefreshClient]
    fun provideRefreshTokenClientRetrofit(
        @TokenRefreshClient okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(MainEndpoint.BASE_URL)
        .addConverterFactory(factory)
        .client(okHttpClient)
        .build()

    @[Provides Singleton PublicClient]
    fun providePublicRetrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(MainEndpoint.BASE_URL)
        .addConverterFactory(factory)
        .client(okHttpClient)
        .build()
}