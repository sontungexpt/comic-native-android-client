package com.comic.android_native_client.ui.activities.index

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comic.android_native_client.constants.NavigationGraph
import com.comic.android_native_client.constants.Screen

private val BOTTOM_TAB_SCREENS = setOf(
    Screen.Home,
    Screen.Explore,
    Screen.Favorite,
    Screen.ProfileGraph,
)

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    onBeforeNavigation: ((Screen) -> Unit)? = null,
    onAfterNavigation: ((Screen) -> Unit)? = null
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (BOTTOM_TAB_SCREENS.none {
            if (it is NavigationGraph &&
                it.startDestination::class.qualifiedName == currentRoute
            ) return@none true
            else it::class.qualifiedName == currentRoute
        }) {
        return
    }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        BOTTOM_TAB_SCREENS.forEach { screen ->
            val isCurrentRoute = if (
                screen is NavigationGraph &&
                screen.startDestination::class.qualifiedName == currentRoute
            ) true else currentRoute == screen::class.qualifiedName

            NavigationBarItem(
                selected = isCurrentRoute,
                onClick = {
                    if (!isCurrentRoute) {
                        onBeforeNavigation?.invoke(screen)
                        navController.navigate(screen) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                        onAfterNavigation?.invoke(screen)
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon(),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.label),
                        color = if (isCurrentRoute)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                    disabledIconColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                    disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                )
            )
        }
    }

}