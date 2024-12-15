package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comic.android_native_client.ui.activities.index.constants.GenreIconMap
import com.comic.android_native_client.ui.components.GenreCard
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = hiltViewModel<ExploreViewModel>(),
    horizontalPadding: Dp = 20.dp
) {
    val context = LocalContext.current
    val uiState = exploreViewModel.screenUiState.collectAsState()

    LaunchedEffect(Unit) {
        exploreViewModel.loadCategories()
    }

    HeaderScreen(
        modifier = Modifier.fillMaxSize(),
        contentPadding = horizontalPadding,
        headerText = "Explore"
    ) {
        if (uiState.value.categoriesFetching) {
            LoadingCircle(
                wrapperModifier = Modifier.fillMaxWidth(),
                modifier = Modifier.size(30.dp),
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = uiState.value.categories,
                    contentType = { it.javaClass },
                    key = { it.id }
                ) { genre ->
                    GenreCard(
                        name = genre.name,
                        icon = GenreIconMap[genre.name],
                        imageUrl = genre.imageUrl,
                    )
                }
            }
        }


    }
}






