package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.lifecycle.ViewModel
import com.comic.android_native_client.network.services.ComicCategoryService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val comicCategoryService: ComicCategoryService
) : ViewModel()
