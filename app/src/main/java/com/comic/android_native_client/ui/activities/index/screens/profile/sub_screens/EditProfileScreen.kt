package com.comic.android_native_client.ui.activities.index.screens.profile.sub_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.comic.android_native_client.ui.components.Avatar
import com.comic.android_native_client.ui.components.common.LoadingIndicatorButton
import com.comic.android_native_client.ui.components.layout.BackFloatingScreen

@Composable
fun EditProfileScreen(
    editProfileViewModel: EditProfileViewModel = hiltViewModel<EditProfileViewModel>(),
    navController: NavHostController,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier
) {
    BackFloatingScreen(
        onBackCLick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {
                        TODO()
                    })
            ) {
                Avatar(
                    imageUrl = editProfileViewModel.avatar,
                    modifier = Modifier.fillMaxSize()
                )
            }

            OutlinedTextField(
                isError = false,
                value = editProfileViewModel.name,
                onValueChange = {
                    editProfileViewModel.updateName(it)
                },
                label = {
                    Text("Username")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = editProfileViewModel.introduction,
                onValueChange = {
                    editProfileViewModel.updateIntroduction(it)
                },
                label = { Text("Introduction") },
                placeholder = { Text("Introduce yourself") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            LoadingIndicatorButton(
                onClick = {
                    editProfileViewModel.updateProfile {
                        navController.popBackStack()
                    }
                },
                shape = MaterialTheme.shapes.medium,
                loading = editProfileViewModel.loading,
                loadingColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Save Changes")
            }
        }
    }


}
