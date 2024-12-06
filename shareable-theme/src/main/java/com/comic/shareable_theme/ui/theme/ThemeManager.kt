package com.comic.shareable_theme.ui.theme

import android.content.Context
import com.comic.shareable_theme.ui.theme.datastore.ThemeDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ThemeManager {
    private var initialized = false
    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val themeScope = CoroutineScope(Dispatchers.IO)

    fun initialize(context: Context) {
        if (!initialized) {
            initialized = true
            themeScope.launch {
                val isDark = ThemeDataStore.getDarkModeState(context).first()
                _isDarkTheme.emit(isDark)
            }
        }
    }

    fun observeDarkModeState(context: Context): Flow<Boolean> =
        ThemeDataStore.getDarkModeState(context).onEach { isDarkMode ->
            _isDarkTheme.emit(isDarkMode)
        }

    suspend fun saveDarkMode(context: Context, isDarkTheme: Boolean) {
        if (_isDarkTheme.value != isDarkTheme) {
            _isDarkTheme.emit(isDarkTheme)
            withContext(Dispatchers.IO) {
                ThemeDataStore.saveDarkMode(context, isDarkTheme)
            }
        }
    }
}
