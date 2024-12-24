package com.comic.android_native_client.ui.activities.index.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.FavoriteIconToggleable
import com.comic.android_native_client.ui.components.common.LoadingCircle
import com.comic.android_native_client.ui.components.common.TextWithIcon

@Composable
fun ComicStatsSection(
    views: Long,
    rating: String,

    favoriteEnabled: Boolean = true,
    favorited: Boolean,
    favoritedChanging: Boolean,
    onToggleFavorited: (favorited: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$views",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge
            )
            TextWithIcon(
                prefixIconTint = MaterialTheme.colorScheme.tertiary,
                prefixIcon = Icons.Filled.Visibility,
                prefixIconModifier = Modifier.size(24.dp),
                text = stringResource(R.string.views),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = rating,
                color = Color(0xFFFFD700),
                style = MaterialTheme.typography.titleLarge
            )

            TextWithIcon(
                prefixIconTint = Color(0xFFFFD700),
                prefixIcon = Icons.Filled.EmojiEvents,
                prefixIconModifier = Modifier.size(24.dp),
                text = stringResource(R.string.rating),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Column(
            modifier = Modifier.clickable {
                onToggleFavorited(!favorited)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (favoritedChanging) {
                LoadingCircle(
                    wrapperModifier = Modifier.height(38.dp),
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                FavoriteIconToggleable(
                    enabled = favoriteEnabled,
                    modifier = Modifier.height(38.dp),
                    iconSize = with(density) {
                        24.sp.toDp()
                    },
                    favorited = favorited,
                    favoritedColor = MaterialTheme.colorScheme.error,
                    onToggleFavorited = onToggleFavorited
                )
            }

            Text(
                text = stringResource(R.string.favorite),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

        }
    }
}

