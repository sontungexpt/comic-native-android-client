package com.comic.android_native_client.ui.activities.index

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comic.android_native_client.constants.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val items = remember {
        listOf(
            Screen.HOME,
            Screen.EXPLORE,
            Screen.FAVORITE,
            Screen.PROFILE
        )
    }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = MaterialTheme.colorScheme.background,
        //contentColor = MaterialTheme.colorScheme.onBackground
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    when (screen) {
                        Screen.HOME -> onHomeClick()
                        Screen.EXPLORE -> onExploreClick()
                        Screen.FAVORITE -> onFavoriteClick()
                        Screen.PROFILE -> onProfileClick()
                        else -> {}
                    }
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = if (currentRoute == screen.route)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primary
                        
//                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.label),
//                        color = if (currentRoute == screen.route)
//                            MaterialTheme.colorScheme.primary
//                        else
//                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                    )
                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.primary,
//                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
//                    selectedTextColor = MaterialTheme.colorScheme.primary,
//                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
//                ),
            )
        }
    }

}