package com.comic.android_native_client.di.module

import com.comic.android_native_client.di.qualifiers.AuthenticatedClient
import com.comic.android_native_client.di.qualifiers.PublicClient
import com.comic.android_native_client.di.qualifiers.TokenRefreshClient
import com.comic.android_native_client.network.constants.MainEndpoint
import com.comic.android_native_client.network.services.auth.AccessTokenInterceptor
import com.comic.android_native_client.network.services.auth.AuthAuthenticator
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpRequestConfig {
    private val networkJson = Json { ignoreUnknownKeys = true }
    private val factory = networkJson.asConverterFactory(
        "application/json; charset=UTF8".toMediaType()
    )
    private val baseRetrofitBuilder = Retrofit.Builder()
        .baseUrl(MainEndpoint.BASE_URL)
        .addConverterFactory(factory)
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val baseOkHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

//    @Provides
//    @Singleton
//    fun provideNetworkMonitor(
//        @ApplicationContext appContext: Context
//    ): NetworkMonitor {
//        return LiveNetworkMonitor(appContext)
//    }

    @[Provides Singleton AuthenticatedClient]
    fun provideAccessOkHttpClient(
//        networkMonitorInterceptor: NetworkMonitorInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient = baseOkHttpClientBuilder
        .authenticator(authAuthenticator)
//        .addInterceptor(networkMonitorInterceptor)
        .addInterceptor(accessTokenInterceptor)
        .build()

    @[Provides Singleton TokenRefreshClient]
    fun provideRefreshOkHttpClient(
//        refreshTokenInterceptor: RefreshTokenInterceptor
    ): OkHttpClient = baseOkHttpClientBuilder
//        .addInterceptor(refreshTokenInterceptor)
        .build()

    @[Provides Singleton PublicClient]
    fun provideUnauthenticatedOkHttpClient(
//        networkMonitorInterceptor: NetworkMonitorInterceptor,
    ): OkHttpClient =
        baseOkHttpClientBuilder
//            .addInterceptor(networkMonitorInterceptor)
            .build()


    @[Provides Singleton AuthenticatedClient]
    fun provideAuthenticatedRetrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit {
        return baseRetrofitBuilder
            .client(okHttpClient)
            .build()
    }

    @[Provides Singleton TokenRefreshClient]
    fun provideRefreshTokenClientRetrofit(
        @TokenRefreshClient okHttpClient: OkHttpClient
    ): Retrofit = baseRetrofitBuilder
        .client(okHttpClient)
        .build()

    @[Provides Singleton PublicClient]
    fun providePublicRetrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit = baseRetrofitBuilder
        .client(okHttpClient)
        .build()


}