package com.comic.android_native_client.ui.activities.authentications

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.AppLogo
import com.comic.android_native_client.ui.components.common.PasswordEditable
import com.comic.shareable_theme.ui.theme.ShareableTheme
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.components.ValidableOutlineTextField
import com.comic.validation_text_field.rememberValidableTextFieldState
import com.comic.validation_text_field.validator.LengthValidator
import com.comic.validation_text_field.validator.PasswordValidator
import com.comic.validation_text_field.validator.RequiredValidator

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
            validators = listOf(RequiredValidator(), LengthValidator(2, 50)),
        )
    }
    val passwordState = remember {
        ValidableTextFieldState(
            fieldName = passwordFieldName.replaceFirstChar { it.uppercaseChar() },
            validators = listOf(RequiredValidator(), PasswordValidator()),
        )
    }
    val fieldWatcher = rememberValidableTextFieldState(emailState, passwordState)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
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

        val cornerShape = RoundedCornerShape(14.dp)
        ValidableOutlineTextField(
            state = emailState,
            label = {
                Text(
                    text = stringResource(R.string.username),
                    style = MaterialTheme.typography.labelMedium
                )
            },
            singleLine = true,
            shape = cornerShape
        )
        Spacer(modifier = Modifier.height(30.dp))

        PasswordEditable(
            state = passwordState,
            shape = cornerShape

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
        
        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = {
                if (fieldWatcher.validateAll()) {
                    onLoginClick(emailState.value, passwordState.value)
                }
            },
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

