package com.comic.android_native_client.ui.components.common.validation

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comic.android_native_client.ui.components.common.validation.validator.EmailValidator
import com.comic.android_native_client.ui.components.common.validation.validator.RequiredValidator
import com.comic.android_native_client.ui.components.common.validation.validator.TextFieldValidator
import com.comic.shareable_theme.ui.theme.ShareableTheme


class ValidableTextFieldState(
    var value: String = "",
    val fieldName: String,
    val validators: List<TextFieldValidator>,
    val lazy: Boolean = false,
) {
    var isValidationAttempted = false
        private set

    private val _valid: MutableState<Boolean> = mutableStateOf(true)

    val valid: Boolean
        get() = _valid.value

    var errorMessage: String = ""
        private set

    fun validate(lazy: Boolean, context: Context? = null): Boolean {
        _valid.value = true
        if (lazy) return true
        isValidationAttempted = true

        for (validator in validators) {
            if (!validator.validate(value)) {
                errorMessage =
                    validator.showErrorMessage(value, fieldName, context)
                _valid.value = false
                break
            }
        }
        return _valid.value
    }

    fun validate(context: Context? = null): Boolean {
        return validate(lazy, context)
    }

    override fun toString(): String {
        return "ValidableTextFieldState(value='$value', fieldName='$fieldName', lazy=$lazy, valid=$_valid, errorMessage='$errorMessage')"

    }
}


class ValidableTextFieldWatcher(
    private vararg val fieldStates: ValidableTextFieldState
) {

    constructor(
        fieldStates: List<ValidableTextFieldState>
    ) : this(*fieldStates.toTypedArray())

    var isAllValid: Boolean = true
        private set


    fun validateAll(context: Context? = null): Boolean {
        isAllValid = true
        for (state in fieldStates) {
            if (state.lazy || !state.isValidationAttempted) {
                state.validate(false, context)
            }
            if (!state.valid) {
                isAllValid = false
            }
        }

        return isAllValid
    }

}

@Composable
fun rememberValidableTextFieldState(
    vararg fieldStates: ValidableTextFieldState
): ValidableTextFieldWatcher {
    return remember {
        ValidableTextFieldWatcher(fieldStates = fieldStates)
    }
}

@Composable
fun ValidableTextField(
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
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(6.dp),
    //shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    var textInput = remember { mutableStateOf(state.value) }
    var context = LocalContext.current

    Column(
        modifier = modifier,
    ) {
        val hasError = !state.valid || additionalErrorCondition
        OutlinedTextField(
            value = textInput.value,
            onValueChange = { newValue ->
                state.value = newValue
                onValueChange(newValue)
                textInput.value = state.value
                state.validate(context)
            },
            enabled = enabled,
            modifier = textInputModifier.fillMaxWidth(),
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
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
        )

        AnimatedVisibility(
            enter = enterErrorTransition,
            exit = exitErrorTransition,
            visible = hasError,
        ) {
            errorField(state.errorMessage)
        }
    }


}


@Composable
@Preview
fun ValidableTextFieldPreview() {
    ShareableTheme {
        Scaffold { innerPadding ->
            var state = remember {
                ValidableTextFieldState(
                    fieldName = "Email",
                    value = "",
                    validators = listOf(RequiredValidator(), EmailValidator()),
                    lazy = true,
                )
            }
            ValidableTextField(
                onValueChange = {
                },
                state = state,
            )
            val fieldWatcher = remember {
                ValidableTextFieldWatcher(state)
            }
            Button(onClick = {
                if (fieldWatcher.validateAll()) {

                } else {

                }
            }) {
                Text("Submit")
            }

        }

    }

}