package com.comic.android_native_client.ui.activities.authentications.screens.login

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comic.android_native_client.R
import com.comic.android_native_client.constants.Screen
import com.comic.android_native_client.ui.activities.index.AppActivity
import com.comic.android_native_client.ui.components.common.AppLogo
import com.comic.android_native_client.ui.components.common.LoadingIndicatorTextButton
import com.comic.android_native_client.ui.components.common.PasswordEditable
import com.comic.shareable_theme.ui.theme.ShareableTheme
import com.comic.validation_text_field.components.ValidableOutlineTextField

@Composable
fun LoginScreen(
    navController: NavController,
    horizontalPadding: Dp,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.error.collect { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(
                    context,
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

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
        Spacer(modifier = Modifier.height(50.dp))

        ValidableOutlineTextField(
            state = viewModel.usernameState,
            label = {
                Text(
                    text = stringResource(R.string.username),
                    style = MaterialTheme.typography.labelMedium
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )
        Spacer(modifier = Modifier.height(20.dp))

        PasswordEditable(
            state = viewModel.passwordState,
            shape = MaterialTheme.shapes.medium
        )

        TextButton(
            onClick = {
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                stringResource(R.string.forgot_password) + "?",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LoadingIndicatorTextButton(
            onClick = {
                viewModel.login({
                    context.startActivity(
                        Intent(context, AppActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                })
            },
            loadingModifier = Modifier.size(24.dp),
            loading = viewModel.loginProcessing
        ) {
            Text(
                text = stringResource(R.string.login),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val textColor = MaterialTheme.colorScheme.onSurface
            Text(
                stringResource(R.string.do_not_have_account_ask),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = {
                navController.navigate(Screen.Register)
            }) {
                Text(
                    stringResource(R.string.register),
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
fun LoginScreenPreview() {
    ShareableTheme {
        Scaffold { innerPadding ->
            LoginScreen(
                viewModel = hiltViewModel(),
                navController = rememberNavController(),
                horizontalPadding = 16.dp
            )
        }
    }
}

