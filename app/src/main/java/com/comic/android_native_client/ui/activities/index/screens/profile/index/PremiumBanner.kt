package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.TextWithIcon


@Composable
fun PremiumBanner(
    paddingX: Int = 16,
    height: Int = 200,
    headlineStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = paddingX.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextWithIcon(
            text = stringResource(id = R.string.upgrade_to_premium),
            style = headlineStyle,
            prefixIcon = Icons.Default.Star,
            prefixIconTint = MaterialTheme.colorScheme.onSurface,
            textModifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        )

        AsyncImage(
            fallback = painterResource(R.drawable.user_background),
            error = painterResource(R.drawable.user_background),
            model = "https://via.placeholder.com/300",
            contentDescription = "Premium Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }


}