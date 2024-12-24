package com.comic.android_native_client.di.module.repository

import com.comic.android_native_client.data.repository.ChapterRepository
import com.comic.android_native_client.data.repository.ComicCategoryRepository
import com.comic.android_native_client.data.repository.ComicRepository
import com.comic.android_native_client.data.repository.CommentRepository
import com.comic.android_native_client.data.repository.FavoriteRepository
import com.comic.android_native_client.data.repository.UserRepository
import com.comic.android_native_client.data.repository.impl.ChapterRepositoryImpl
import com.comic.android_native_client.data.repository.impl.ComicCategoryRepositoryImpl
import com.comic.android_native_client.data.repository.impl.ComicRepositoryImpl
import com.comic.android_native_client.data.repository.impl.CommentRepositoryImpl
import com.comic.android_native_client.data.repository.impl.FavoriteRepositoryImpl
import com.comic.android_native_client.data.repository.impl.UserRepositoryImpl
import com.comic.android_native_client.network.services.ChapterService
import com.comic.android_native_client.network.services.ComicCategoryService
import com.comic.android_native_client.network.services.ComicService
import com.comic.android_native_client.network.services.CommentService
import com.comic.android_native_client.network.services.FavoriteComicService
import com.comic.android_native_client.network.services.UserService
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

    @[Provides Singleton]
    fun provideCommentRepository(
        commentService: CommentService
    ): CommentRepository {
        return CommentRepositoryImpl(
            commentService = commentService
        )
    }

    @[Provides Singleton]
    fun provideFavoriteRepository(
        favoriteComicService: FavoriteComicService
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(
            favoriteComicService = favoriteComicService
        )
    }

    @[Provides Singleton]
    fun provideComicRepository(
        comicService: ComicService
    ): ComicRepository {
        return ComicRepositoryImpl(
            comicService
        )
    }

    @[Provides Singleton]
    fun provideUserRepository(
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(
            userService = userService
        )
    }

    @[Provides Singleton]
    fun provideChapterRepository(
        chapterService: ChapterService
    ): ChapterRepository {
        return ChapterRepositoryImpl(
            chapterService
        )
    }
}