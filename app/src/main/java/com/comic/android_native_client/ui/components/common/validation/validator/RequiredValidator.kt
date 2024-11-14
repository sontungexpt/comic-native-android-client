package com.comic.android_native_client.ui.components.common.validation.validator

import android.content.Context
import com.comic.android_native_client.R
import java.util.Locale


class RequiredValidator : TextFieldValidator {
    override fun validate(value: String): Boolean {
        return value.isNotEmpty()
    }

    override fun showErrorMessage(
        value: String,
        fieldName: String,
        context: Context?
    ): String {
        return context?.resources?.getString(
            R.string.required_field_msg,
            fieldName.lowercase(Locale.getDefault())
        ) ?: "This ${fieldName.lowercase(Locale.getDefault())} must not be blank"
    }
}