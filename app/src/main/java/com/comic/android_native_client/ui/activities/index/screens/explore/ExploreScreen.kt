package com.comic.android_native_client.ui.activities.index.screens.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.constants.GenreIconMap
import com.comic.android_native_client.ui.components.GenreCard
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    navController: NavController,
    exploreViewModel: ExploreViewModel = hiltViewModel<ExploreViewModel>(),
    horizontalPadding: Dp = 20.dp
) {
    val context = LocalContext.current
    val uiState = exploreViewModel.screenUiState.collectAsState()

    LaunchedEffect(Unit) {
        exploreViewModel.getCategories()
    }

    HeaderScreen(
        modifier = Modifier.fillMaxSize(),
        contentPadding = horizontalPadding,
        header = {
            DockedSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface
//                    dividerColor = Color.Transparent
                ),
                inputField = {
                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(

                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        value = "",
                        placeholder = {
                            Text(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                modifier = Modifier.padding(start = 10.dp),
                                text = stringResource(R.string.input_comic_name)
                            )
                        },
                        onValueChange = {

                        },
                        trailingIcon = {
                            IconButton(
                                modifier = Modifier.padding(end = 10.dp),
                                onClick = {
                                    navController.navigate(Screen.Search)
                                }
                            ) {
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                            }
                        }
                    )
                },
                expanded = false,
                onExpandedChange = {},
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = horizontalPadding)
                ) {
                    Text("test")

                }

            }

        }
    ) {

        Column {
            Text(
                text = stringResource(R.string.category),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            )
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
}






