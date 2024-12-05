package com.comic.validation_text_field.validator

import android.content.Context
import android.util.Patterns
import com.comic.validation_text_field.R

class EmailValidator(
    private var errorMessage: String? = null
) : TextFieldValidator {
    override fun validate(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }

    override fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return errorMessage ?: context?.resources?.getString(
            R.string.validatation_text_field_invalid_email_address_msg,
            value
        )
        ?: "This string $value is not a valid email address"
    }
}

