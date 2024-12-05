package com.comic.shareable_theme.ui.theme.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.comic.shareable_theme.ui.theme.utils.isSystemInDarkTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("theme_preferences")

object ThemeDataStore {
    private val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")


    fun getDarkModeState(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_DARK_THEME]
                ?: isSystemInDarkTheme(context)
        }
    }

    suspend fun saveDarkMode(context: Context, isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_THEME] = isDarkTheme
        }
    }
}