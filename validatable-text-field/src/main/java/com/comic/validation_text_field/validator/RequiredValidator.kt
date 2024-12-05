package com.comic.validation_text_field.validator

import android.content.Context
import com.comic.validation_text_field.R
import java.util.Locale


class RequiredValidator(
    val errorMessage: String? = null
) : TextFieldValidator {
    override fun validate(value: String): Boolean {
        return value.isNotEmpty()
    }

    override fun showErrorMessage(
        value: String,
        fieldName: String,
        context: Context?
    ): String {
        return errorMessage ?: context?.resources?.getString(
            R.string.validatation_text_field_required_field_msg,
            fieldName.lowercase(Locale.getDefault())
        ) ?: "This ${fieldName.lowercase(Locale.getDefault())} must not be blank"
    }
}