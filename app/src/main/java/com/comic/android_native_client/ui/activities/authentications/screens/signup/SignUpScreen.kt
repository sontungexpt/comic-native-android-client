package com.comic.android_native_client.ui.activities.authentications.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.components.common.AppLogo
import com.comic.android_native_client.ui.components.common.LoadingIndicatorTextButton
import com.comic.android_native_client.ui.components.common.PasswordEditable
import com.comic.shareable_theme.ui.theme.ShareableTheme
import com.comic.validation_text_field.components.ValidableOutlineTextField

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel<SignUpViewModel>(),
    navController: NavHostController,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = horizontalPadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppLogo(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(14.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ValidableOutlineTextField(
                shape = MaterialTheme.shapes.medium,
                state = viewModel.nameState,
                label = { Text(stringResource(R.string.name)) },
            )
            ValidableOutlineTextField(
                shape = MaterialTheme.shapes.medium,
                state = viewModel.usernameState,
                label = { Text(stringResource(R.string.username)) },
            )
            PasswordEditable(
                shape = MaterialTheme.shapes.medium,
                state = viewModel.passwordState,
            )

            ValidableOutlineTextField(
                shape = MaterialTheme.shapes.medium,
                state = viewModel.matchPasswordState,
                label = { Text(stringResource(R.string.match_passowrd)) },
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LoadingIndicatorTextButton(
            onClick = {
                viewModel.signUp(navController)
            },
            loadingModifier = Modifier.size(24.dp),
            loading = viewModel.isLoading
        ) {
            Text(
                text = stringResource(R.string.register),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.already_have_account_ask),
                color = MaterialTheme.colorScheme.onBackground,
            )
            TextButton(onClick = {
                navController.navigate(Screen.Login)
            }) {
                Text(
                    stringResource(R.string.login),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }
}


@Composable
@Preview
fun SignUpScreenPreview() {
    ShareableTheme {
        Scaffold { innerPadding ->
            SignUpScreen(
                navController = rememberNavController(),
                horizontalPadding = 16.dp,
            )
        }
    }
}
