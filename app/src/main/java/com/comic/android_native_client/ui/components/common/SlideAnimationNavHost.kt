package com.comic.android_native_client.ui.components.common

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun SlideAnimationNavHost(
    animationDuration: Int = 300,
    navController: NavHostController,
    startDestination: Any,
    contentAlignment: Alignment = Alignment.TopStart,
    enterDirection: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.Start,
    exitDirection: AnimatedContentTransitionScope.SlideDirection = AnimatedContentTransitionScope.SlideDirection.End,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        contentAlignment = contentAlignment,
        builder = builder,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(animationDuration, easing = LinearEasing),
                towards = enterDirection,
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(animationDuration, easing = LinearEasing),
                towards = exitDirection,
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = enterDirection,
                animationSpec = tween(animationDuration, easing = LinearEasing)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = exitDirection,
                animationSpec = tween(animationDuration, easing = LinearEasing)
            )
        }
    )
}
