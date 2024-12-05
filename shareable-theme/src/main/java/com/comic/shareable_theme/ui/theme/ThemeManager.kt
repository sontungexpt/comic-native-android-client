package com.comic.shareable_theme.ui.theme

import android.content.Context
import com.comic.shareable_theme.ui.theme.datastore.ThemeDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object ThemeManager {
    private var _init = false
    private val _isDarkTheme = MutableStateFlow<Boolean>(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()
    private val mutex = Mutex()
    fun initialize(context: Context) {
        if (_init == false) {
            _init = true
            GlobalScope.launch {
                _isDarkTheme.value = ThemeDataStore.getDarkModeState(context).first()
            }
        }
    }

    suspend fun getDarkModeState(context: Context): Flow<Boolean> {
        return ThemeDataStore.getDarkModeState(context).also { flow ->
            flow.collect { theme ->
                _isDarkTheme.value = theme
            }
        }
    }

    suspend fun saveDarkMode(context: Context, isDarkTheme: Boolean) {
        mutex.withLock {
            if (_isDarkTheme.value == isDarkTheme) {
                return
            }
            _isDarkTheme.value = isDarkTheme
            ThemeDataStore.saveDarkMode(context, isDarkTheme)
        }
    }
}

