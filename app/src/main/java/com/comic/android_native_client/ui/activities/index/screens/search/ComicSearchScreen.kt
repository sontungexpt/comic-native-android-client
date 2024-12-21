package com.comic.android_native_client.ui.activities.index.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.layout.HeaderScreen

@OptIn(ExperimentalMaterial3Api::class)
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
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(

                ),
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                value = "search",
                placeholder = {
                    Text(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(start = 4.dp),
                        text = stringResource(R.string.input_comic_name)
                    )
                },
                onValueChange = {

                },
                leadingIcon = {
                    if (searchViewModel.loading) {
                        LoadingCircle(
                            wrapperModifier = Modifier.padding(start = 10.dp),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
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
        }
    ) {
//        LazyColumn {
//            items()
//
//        }

    }

}
