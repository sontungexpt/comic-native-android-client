package com.comic.validation_text_field.validator

import android.content.Context

interface TextFieldValidator {
    fun validate(value: String): Boolean

    fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return "$fieldName is invalid"
    }
}
