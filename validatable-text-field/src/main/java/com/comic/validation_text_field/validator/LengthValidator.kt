package com.comic.validation_text_field.validator

import android.content.Context

class LengthValidator(
    private val minLength: Int,
    private val maxLength: Int,
    private val errorMessage: String? = null
) : TextFieldValidator {

    override fun validate(value: String): Boolean {
        return value.length in minLength..maxLength
    }

    override
    fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return errorMessage ?: "This string $value is not in the range of $minLength and $maxLength"

    }
}
