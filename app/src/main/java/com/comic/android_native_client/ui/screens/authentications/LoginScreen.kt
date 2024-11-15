package com.comic.android_native_client.ui.screens.authentications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.AppLogo
import com.comic.android_native_client.ui.components.common.PasswordEditable
import com.comic.android_native_client.ui.components.common.validation.ValidableTextField
import com.comic.android_native_client.ui.components.common.validation.ValidableTextFieldState
import com.comic.android_native_client.ui.components.common.validation.rememberValidableTextFieldState
import com.comic.android_native_client.ui.components.common.validation.validator.RequiredValidator
import com.comic.shareable_theme.ui.theme.ShareableTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onFacebookLoginClick: () -> Unit = {} // Added parameter for Facebook login
) {
    val context = LocalContext.current
    val usernameFieldName = stringResource(id = R.string.username)
    val passwordFieldName = stringResource(id = R.string.password)

    val emailState = remember {
        ValidableTextFieldState(
            fieldName = usernameFieldName.replaceFirstChar { it.uppercaseChar() },
            validators = listOf(RequiredValidator())
        )
    }
    val passwordState = remember {
        ValidableTextFieldState(
            fieldName = passwordFieldName.replaceFirstChar { it.uppercaseChar() },
            validators = listOf(RequiredValidator()),
        )
    }
    val fieldWatcher = rememberValidableTextFieldState(emailState, passwordState)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        AppLogo(modifier = Modifier.size(100.dp))
        Text(
            text = stringResource(R.string.loginTitle),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))

        ValidableTextField(
            state = emailState,
            label = { Text(stringResource(R.string.username)) },
            singleLine = true,
        )

        PasswordEditable(
            state = passwordState,
        )

        TextButton(
            onClick = onForgotPasswordClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                stringResource(R.string.forgot_password) + "?",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(
            onClick = {
                if (fieldWatcher.validateAll()) {
                    onLoginClick(emailState.value, passwordState.value)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            shape = RoundedCornerShape(14.dp),
            onClick = onGoogleLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Image(
                painter = painterResource(R.drawable.google_logo), // Icon Google (thêm ảnh vào drawable)
                contentDescription = stringResource(R.string.google_login),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign in with Google", color = Color.White)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val textColor = MaterialTheme.colorScheme.onSurface
            Text(
                stringResource(R.string.do_not_have_account_ask),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(onClick = onSignUpClick) {
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
            LoginScreen()
        }
    }
}

