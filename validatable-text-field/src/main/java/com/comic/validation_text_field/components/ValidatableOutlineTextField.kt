package com.comic.validation_text_field.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.comic.validation_text_field.ValidableTextFieldState

@Composable
fun ValidableOutlineTextField(
    state: ValidableTextFieldState,
    onValueChange: ((String) -> Unit) = {},
    modifier: Modifier = Modifier,
    textInputModifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    errorField: @Composable() ((errorMessage: String) -> Unit) = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = it,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.error
        )
    },
    additionalErrorCondition: Boolean = false,
    enterErrorTransition: EnterTransition = EnterTransition.None,
    exitErrorTransition: ExitTransition = fadeOut() + shrinkVertically(),
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(6.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    var context = LocalContext.current
    val hasError = !state.valid || additionalErrorCondition

    OutlinedTextField(
        value = state.value,
        onValueChange = { newValue ->
            state.value = newValue
            onValueChange(newValue)
            state.validate(context)
        },
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        isError = hasError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        supportingText = {
            AnimatedVisibility(
                enter = enterErrorTransition,
                exit = exitErrorTransition,
                visible = hasError,
            ) {
                errorField(state.errorMessage)
            }
        }
    )
}