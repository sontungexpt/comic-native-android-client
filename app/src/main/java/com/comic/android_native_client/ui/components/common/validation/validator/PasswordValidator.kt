package com.comic.android_native_client.ui.components.common.validation.validator

import android.content.Context
import com.comic.android_native_client.R

class PasswordValidator(
    val regex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d@$!%*?&]{8,}$"),
    val errorMessage: String? = null
) : TextFieldValidator {

    override fun validate(value: String): Boolean {
        return regex.matches(value)
    }

    override fun showErrorMessage(
        value: String, fieldName: String,
        context: Context?
    ): String {
        return errorMessage ?: context?.resources?.getString(R.string.invalid_password_msg)
        ?: "Password must have 1 upcase char and at least 8 character"
    }
}