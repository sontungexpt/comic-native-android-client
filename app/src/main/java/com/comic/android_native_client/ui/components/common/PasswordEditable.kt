package com.comic.android_native_client.ui.components.common

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.comic.validation_text_field.ValidableTextFieldState
import com.comic.validation_text_field.components.ValidableOutlineTextField

@Composable
fun PasswordEditable(
    state: ValidableTextFieldState,
    onPasswordChange: (String) -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    shape: Shape = OutlinedTextFieldDefaults.shape,
    additionalErrorCondition: Boolean = false,
    enterErrorTransition: EnterTransition = EnterTransition.None,
    exitErrorTransition: ExitTransition = fadeOut() + shrinkVertically(),
    leadingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
) {
    val passwordVisible = remember { mutableStateOf(false) }
    return ValidableOutlineTextField(
        state = state,
        additionalErrorCondition = additionalErrorCondition,
        enterErrorTransition = enterErrorTransition,
        exitErrorTransition = exitErrorTransition,
        onValueChange = onPasswordChange,
        enabled = enabled,
        label = {
            if (label != null) {
                label()
            } else {
                Text(
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        colors = colors,
        readOnly = readOnly,
        singleLine = true,
        shape = shape,
        maxLines = maxLines,
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
