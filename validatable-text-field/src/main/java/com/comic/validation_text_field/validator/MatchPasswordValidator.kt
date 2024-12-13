package com.comic.validation_text_field.validator

import android.content.Context
import com.comic.validation_text_field.R
import com.comic.validation_text_field.ValidableTextFieldState

class MatchPasswordValidator(
    private val matchedPasswordState: ValidableTextFieldState,
    private var errorMessage: String? = null
) : TextFieldValidator {
    override fun validate(value: String): Boolean {
        return value == matchedPasswordState.value
    }

    override fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return errorMessage ?: context?.resources?.getString(
            R.string.validatation_text_field_password_not_match_msg,
        )
        ?: "Password does not match"
    }

}
