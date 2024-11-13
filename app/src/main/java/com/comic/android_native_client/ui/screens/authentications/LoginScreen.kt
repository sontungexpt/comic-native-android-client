package com.comic.android_native_client.ui.screens.authentications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import com.comic.android_native_client.ui.theme.AppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, password: String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        AppLogo(
            modifier = Modifier
                .width(100.dp)
        )
        Text(
            text = stringResource(R.string.loginTitle),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        )

        PasswordEditable(
            password = password.value,
            onPasswordChange = { password.value = it },
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(
            onClick = onForgotPasswordClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Forgot Password?", color = Color.White)
        }

        Button(
            onClick = { onLoginClick(email.value, password.value) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
@Preview
fun LoginScreenPreview() {
    AppTheme {
        Scaffold { innerPadding ->
            LoginScreen(
                onLoginClick = { username, password -> {} },
                onForgotPasswordClick = {},
                onSignUpClick = {},

                )
        }
    }
}
