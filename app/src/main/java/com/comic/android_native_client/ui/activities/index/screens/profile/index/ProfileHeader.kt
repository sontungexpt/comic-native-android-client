package com.comic.android_native_client.ui.activities.index.screens.profile.index

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.Avatar

@Composable
fun ProfileHeader(
    onEditProfile: () -> Unit,
    onSettingsClick: () -> Unit,

    userBackgroundUrl: String? = null,
    avatarUrl: String? = null,
    username: String = "User",
    introduction: String = "",

    imageHeight: Int = 240,
    paddingX: Int = 16,
    modifier: Modifier = Modifier,

    user_level: Int = 1,
    currentExp: Float = 40f,
    maxExp: Float = 100f
) {
    var avatarOffset = 60
    if (imageHeight < avatarOffset) {
        avatarOffset = imageHeight / 2
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // background image and avatar
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val defaultBackground = painterResource(R.drawable.user_background)
            AsyncImage(
                model = userBackgroundUrl,
                error = defaultBackground,
                fallback = defaultBackground,
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(imageHeight.dp)
            )

            // Settings Button
            IconButton(
                onClick = {
                    onSettingsClick()
                },
                colors = IconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContainerColor = Color(0x80000000),
                    disabledContentColor = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    modifier = Modifier.aspectRatio(1f),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Avatar(
                imageUrl = avatarUrl,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = avatarOffset.dp)
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.surfaceContainer, CircleShape),
            )
        }


        // Main content
        Column(
            modifier = Modifier
                .padding(top = (avatarOffset + 10).dp)
                .padding(horizontal = paddingX.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {


            Text(
                text = username,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Button(
                onClick = {
                    onEditProfile()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Level $user_level",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    imageVector = Icons.Filled.EmojiEvents,
                    contentDescription = "Points",

                    // yellow
                    tint = Color(0xFFFFD700)
                )
                LevelBar(
                    filledValue = currentExp,
                    maxValue = maxExp,
                    valueTextStyle = TextStyle(fontSize = 14.sp)
                )
            }

            if (introduction.isNotEmpty()) {
                Text(
                    text = introduction,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = "No introduction yet",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


        }
    }
}