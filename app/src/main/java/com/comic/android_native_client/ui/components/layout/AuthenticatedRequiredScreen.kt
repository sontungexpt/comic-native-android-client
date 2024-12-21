package com.comic.android_native_client.ui.components.layout

import androidx.compose.runtime.Composable

@Composable
fun AuthenticatedRequiredScreen(
    loggedIn: () -> Boolean,
    onNavigateToHome: () -> Unit,
    onNavigateToAuth: () -> Unit,
    title: String = "Welcome to Comic",
    message: String = "Please log in to continue or explore the app.",
    content: @Composable () -> Unit,
) {
    if (!loggedIn()) {
        UnauthenticatedScreen(
            title = title,
            message = message,
            onLoginClick = {
                onNavigateToAuth()
            },
            onHomeClick = {
                onNavigateToHome()
            }
        )
        return
    }
    content()
}