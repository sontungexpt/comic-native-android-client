package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comic.android_native_client.common.HttpResult
import com.comic.android_native_client.data.model.ComicCategory
import com.comic.android_native_client.data.repository.ComicCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ExploreScreenState(
    val categoriesFetching: Boolean,
    val categories: List<ComicCategory>
)

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val comicCategoryRepository: ComicCategoryRepository
) : ViewModel() {
    private val _screenUiState = MutableStateFlow(
        ExploreScreenState(
            categories = emptyList(),
            categoriesFetching = true
        )
    )
    val screenUiState = _screenUiState.asStateFlow()

    fun getCategories() {
        _screenUiState.value = _screenUiState.value.copy(
            categoriesFetching = true
        )
        viewModelScope.launch {
            when (val result = comicCategoryRepository.getComicCategories()) {
                is HttpResult.Success -> {
                    _screenUiState.value = _screenUiState.value.copy(
                        categories = result.data,
                        categoriesFetching = false
                    )
                }

                is HttpResult.Error -> {
                    _screenUiState.value = _screenUiState.value.copy(
                        categories = emptyList(),
                        categoriesFetching = false
                    )
                }

                else -> {
                    _screenUiState.value = _screenUiState.value.copy(
                        categories = emptyList(),
                        categoriesFetching = false
                    )
                }
            }
        }
    }

}
