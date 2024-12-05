package com.comic.android_native_client.ui.activities.authentications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.R
import com.comic.android_native_client.ui.components.common.AppLogo
import com.comic.android_native_client.ui.components.common.PasswordEditable
import com.comic.shareable_theme.ui.theme.ShareableTheme
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.components.ValidableOutlineTextField
import com.comic.validation_text_field.rememberValidableTextFieldState
import com.comic.validation_text_field.validator.PasswordValidator
import com.comic.validation_text_field.validator.RequiredValidator

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, password: String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val usernameFieldName = stringResource(id = R.string.username)
    val passwordFieldName = stringResource(id = R.string.password)
    val nameFieldName = stringResource(id = R.string.name)

    val emailState = remember {
        ValidableTextFieldState(
            fieldName = usernameFieldName,
            validators = listOf(RequiredValidator())
        )
    }
    val passwordState = remember {
        ValidableTextFieldState(
            fieldName = passwordFieldName,
            validators = listOf(RequiredValidator(), PasswordValidator()),
        )
    }

    val nameState = remember {
        ValidableTextFieldState(
            fieldName = nameFieldName,
            validators = listOf(RequiredValidator()),
        )
    }

    val fieldWatcher = rememberValidableTextFieldState(emailState, passwordState, nameState)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        AppLogo(
            modifier = Modifier
                .width(100.dp)
        )
        Text(
            text = stringResource(R.string.loginTitle),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(14.dp))

        ValidableOutlineTextField(
            state = emailState,
            label = { Text(usernameFieldName) },
        )

        PasswordEditable(
            state = passwordState,
        )

        ValidableOutlineTextField(
            state = nameState,
            label = { Text(nameFieldName) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (fieldWatcher.validateAll()) {
                    onLoginClick(emailState.value, passwordState.value)
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(R.string.register))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val textColor = MaterialTheme.colorScheme.onSurface
            Text(
                stringResource(R.string.already_have_account_ask),
                color = textColor,
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(onClick = onSignUpClick) {
                Text(
                    stringResource(R.string.login),
                    color = textColor,
                    style = MaterialTheme.typography.titleLarge,
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
                onLoginClick = { username, password -> {} },
                onForgotPasswordClick = {},
                onSignUpClick = {},

                )
        }
    }
}
