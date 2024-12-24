package com.comic.android_native_client.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.comic.android_native_client.constants.DataStoreName
import com.comic.android_native_client.data.repository.JwtRepository
import com.comic.android_native_client.data.repository.impl.JwtRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object JwtDatastoreConfig {
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile(DataStoreName.JWT_PREFERCENES.name) }
        )
    }

    @[Provides Singleton]
    fun provideJwtRepository(dataStore: DataStore<Preferences>): JwtRepository {
        return JwtRepositoryImpl(dataStore = dataStore)
    }
}
