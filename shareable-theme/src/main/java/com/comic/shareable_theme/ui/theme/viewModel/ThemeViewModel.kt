package com.comic.shareable_theme.ui.theme.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.shareable_theme.ui.theme.datastore.ThemeDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow<Boolean>(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme


    init {
        viewModelScope.launch {
            _isDarkTheme.value = ThemeDataStore.getDarkModeState(context)
                .first()
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = _isDarkTheme.value
            ThemeDataStore.saveDarkMode(context, newTheme)
            _isDarkTheme.value = newTheme
        }
    }
}
