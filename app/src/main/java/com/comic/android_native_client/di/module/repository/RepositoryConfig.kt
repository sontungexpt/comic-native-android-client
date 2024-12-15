package com.comic.android_native_client.di.module.repository

import com.comic.android_native_client.data.repository.ComicCategoryRepository
import com.comic.android_native_client.data.repository.impl.ComicCategoryRepositoryImpl
import com.comic.android_native_client.network.services.ComicCategoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryConfig {

    @[Provides Singleton]
    fun provideComicCategoryRepository(
        comicCategoryService: ComicCategoryService
    ): ComicCategoryRepository {
        return ComicCategoryRepositoryImpl(
            comicCategoryService
        )
    }
}