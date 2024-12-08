package com.comic.validation_text_field.validator

import android.content.Context
import com.comic.validation_text_field.R
 
class PasswordValidator(
    val regex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])[A-Za-z0-9@$!%*?&]{8,}$"),
    val errorMessage: String? = null
) : TextFieldValidator {

    override fun validate(value: String): Boolean {
        return regex.matches(value)
    }

    override fun showErrorMessage(
        value: String, fieldName: String,
        context: Context?
    ): String {
        return errorMessage
            ?: context?.resources?.getString(R.string.validatation_text_field_invalid_password_msg)
            ?: "Password must contain at least one uppercase letter, one lowercase letter, and be at least 8 characters long."
    }
}