package com.comic.android_native_client.ui.components.common.validation.validator

import android.content.Context
import android.util.Patterns
import com.comic.android_native_client.R

class EmailValidator : TextFieldValidator {
    override fun validate(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }

    override fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return context?.resources?.getString(R.string.invalid_email_address_msg, value)
            ?: "This string $value is not a valid email address"
    }
}

