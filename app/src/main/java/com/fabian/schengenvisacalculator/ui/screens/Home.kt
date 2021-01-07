package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import com.fabian.schengenvisacalculator.ui.navigation.bottomNavScreens
import com.fabian.schengenvisacalculator.ui.navigation.startScreen
import com.fabian.schengenvisacalculator.ui.providers.AmbientNavigator

@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = AmbientNavigator.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                bottomNavScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.imageVector) },
                        label = { Text(stringResource(screen.label)) },
                        // TODO: 7.1.21 The second condition is there because sometimes the route for the root screen is null
                        selected = currentRoute == screen.route || (screen == startScreen && currentRoute == null),
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        })
    { paddingValues ->
        BottomBarScreensHost(modifier = Modifier.padding(paddingValues))
    }
}