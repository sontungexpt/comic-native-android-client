package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.ui.components.common.TextWithIcon
import com.comic.shareable_theme.ui.theme.ui.RowThemeChoosable

@Composable
fun ProfileScreen(
    horizontalPadding: Dp = 18.dp,
) {
    val paddingX = 16
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ProfileHeader(
                paddingX = paddingX,
                imageHeight = 300,
            )
        }


        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = paddingX.dp)

            ) {
                TextWithIcon(
                    prefixIcon = Icons.Filled.FormatColorFill,
                    text = "Theme",
                    prefixIconTint = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium
                )

                RowThemeChoosable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                )

            }


        }
        item {
            PremiumBanner(
                paddingX = paddingX,
                height = 200
            )

        }

    }
}


@Composable
fun InfoList(
    onNavigate: (String) -> Unit
) {
    Column {
        InfoItem(
            text = "Điều khoản sử dụng",
            icon = Icons.Default.Star,
            onClick = { onNavigate("terms") }
        )
        InfoItem(
            text = "Chính sách bảo mật",
            icon = Icons.Default.Shield,
            onClick = { onNavigate("privacy") }
        )
        InfoItem(
            text = "Giới thiệu chúng tôi",
            icon = Icons.Default.Info,
            onClick = { onNavigate("about") }
        )
    }
}

@Composable
fun InfoItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}


