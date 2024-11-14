package com.comic.android_native_client.ui.components.common.validation.validator

import android.content.Context

interface TextFieldValidator {
    fun validate(value: String): Boolean

    fun showErrorMessage(value: String, fieldName: String, context: Context?): String {
        return "$fieldName is invalid"
    }
}
