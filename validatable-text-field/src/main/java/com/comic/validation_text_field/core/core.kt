package com.comic.validation_text_field

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.comic.validation_text_field.validator.TextFieldValidator


class ValidableTextFieldState(
    value: String = "",
    val fieldName: String,
    val validators: List<TextFieldValidator>,
    val lazy: Boolean = false,
) {
    var _value: MutableState<String> = mutableStateOf(value)
    var value: String
        get() = _value.value
        set(value) {
            _value.value = value
        }

    private val _valid: MutableState<Boolean> = mutableStateOf(true)
    val valid: Boolean
        get() = _valid.value

    var isValidationAttempted = false
        private set

    var errorMessage: String = ""
        private set

    fun validate(lazy: Boolean, context: Context? = null): Boolean {
        _valid.value = true
        if (lazy) return true
        isValidationAttempted = true

        for (validator in validators) {
            if (!validator.validate(_value.value)) {
                errorMessage =
                    validator.showErrorMessage(_value.value, fieldName, context)
                _valid.value = false
                break
            }
        }
        return _valid.value
    }

    fun validate(context: Context? = null): Boolean {
        return validate(lazy, context)
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
