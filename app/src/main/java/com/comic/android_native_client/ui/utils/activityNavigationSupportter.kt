package com.comic.android_native_client.ui.utils

import android.content.Context
import android.content.Intent
import com.comic.android_native_client.ui.activities.authentications.AuthActivity
import com.comic.android_native_client.ui.activities.index.AppActivity

fun navigateToApp(
    context: Context,
    intent: Intent = Intent(
        context,
        AppActivity::class.java
    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
) {
    context.startActivity(intent)
}

fun navigateToAuth(
    context: Context,
    intent: Intent = Intent(context, AuthActivity::class.java)
) {
    context.startActivity(intent)
}