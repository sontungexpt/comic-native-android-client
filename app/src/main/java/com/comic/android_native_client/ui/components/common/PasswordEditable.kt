package com.comic.android_native_client.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.comic.android_native_client.R

@Composable
fun PasswordEditable(
    password: String,
    onPasswordChange: (String) -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    shape: Shape = OutlinedTextFieldDefaults.shape,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
) {
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        enabled = enabled,
        label = { Text(text = stringResource(R.string.password)) },
        colors = colors,
        isError = isError,
        readOnly = readOnly,
        singleLine = true,
        shape = shape,
        leadingIcon = leadingIcon,
        supportingText = supportingText,
        prefix = prefix,
        suffix = suffix,
        placeholder = { Text(text = stringResource(R.string.passwordExp)) },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (passwordVisible.value)
                    Icons.Default.Visibility
                else
                    Icons.Default.VisibilityOff
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    imageVector = image,
                    contentDescription =
                    if (passwordVisible.value)
                        stringResource(R.string.hidePassword)
                    else
                        stringResource(R.string.showPassword)
                )
            }
        },
        modifier = modifier
    )
}
