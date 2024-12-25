package com.comic.android_native_client.ui.activities.index.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.ComicCard
import com.comic.android_native_client.ui.components.common.BackIconButton
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@Composable
fun ComicSearchScreen(
    searchViewModel: ComicSearchViewModel = hiltViewModel<ComicSearchViewModel>(),
    navController: NavHostController,
    horizontalPadding: Dp
) {

    HeaderScreen(
        modifier = Modifier
            .padding(horizontal = horizontalPadding),
        header = {
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    value = searchViewModel.searchQuery,
                    onValueChange = {
                        searchViewModel.updateSearchQuery(it)
                        searchViewModel.debounceSearch()
                    },
                    leadingIcon = {
                        BackIconButton(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .background(MaterialTheme.colorScheme.surfaceDim),
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                    },
                    placeholder = {
                        Text(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(start = 4.dp),
                            text = stringResource(R.string.input_comic_name)
                        )
                    },

                    trailingIcon = {
                        IconButton(
                            modifier = Modifier
                                .padding(end = 10.dp),
                            onClick = {
                                searchViewModel.initSearch()
                            }
                        ) {
                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                    }
                )
            }


        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            items(
                items = searchViewModel.foundComics,
                key = { it.id },
                contentType = { it.javaClass }
            ) {
                ComicCard(
                    name = it.name,
                    imageUrl = it.thumbnailUrl,
                    authors = it.authors.map { it.name },
                    newChapters = it.newChapters.map { it.num.toString() },
                    onClick = {
                        navController.navigate(Screen.ComicDetail(
                            id = it.id,
                            imageUrl = it.thumbnailUrl,
                            name = it.name,
                            sourceName = it.originalSource.name,
                            genres = it.categories.map {
                                it.name
                            }
                        ))
                    }
                )
            }

            item(key = "loading") {
                if (!searchViewModel.lastPageReached) {
                    LoadingCircle(
                        loading = searchViewModel.loading,
                        wrapperModifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth(),
                        modifier = Modifier.size(30.dp),
                    )
                    LaunchedEffect(Unit) {
                        searchViewModel.loadMore()
                    }

                }

            }
        }

    }
}
