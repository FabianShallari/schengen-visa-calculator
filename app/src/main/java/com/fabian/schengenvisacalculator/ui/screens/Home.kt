package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import com.fabian.schengenvisacalculator.ui.Screen
import com.fabian.schengenvisacalculator.ui.providers.NavControllerAmbient
import com.fabian.schengenvisacalculator.ui.screens
import com.fabian.schengenvisacalculator.ui.startDestination
import timber.log.Timber
import java.time.LocalDate

@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = NavControllerAmbient.current

    Scaffold(bottomBar = {
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
            Timber.d("Current route: %s", currentRoute)

            screens.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(asset = screen.iconAsset) },
                    label = {
                        Text(
                            text = stringResource(id = screen.label),
                            style = MaterialTheme.typography.caption
                        )
                    },
                    selected = currentRoute == screen.route,
                    selectedContentColor = MaterialTheme.colors.onSurface,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(route = screen.route) {
                                popUpTo = navController.graph.startDestination
                            }
                        }
                    })
            }
        }
    }) { paddingValues ->
        NavigationHost(modifier = Modifier.padding(paddingValues))
    }
}


@Composable
private fun NavigationHost(modifier: Modifier = Modifier) {
    val navController = NavControllerAmbient.current

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Screen.CompletedDays.route) {
            CompletedDays(
                modifier = modifier, completedDatesList = listOf(
                    LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8),
                    LocalDate.of(2020, 3, 16)..LocalDate.of(2020, 8, 31),
                    LocalDate.of(2020, 4, 8)..LocalDate.of(2020, 4, 17)
                )
            )
        }
        composable(Screen.Calendar.route) {
            Calendar(
                modifier = modifier,
                calendarDateRange = LocalDate.of(2020, 1, 12)..LocalDate.of(2020, 12, 31),
                selectedDateRanges = listOf(
                    LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8),
                    LocalDate.of(2020, 3, 16)..LocalDate.of(2020, 3, 31),
                    LocalDate.of(2020, 4, 8)..LocalDate.of(2020, 4, 17)
                )
            )
        }
        composable(Screen.Info.route) {
            Info(modifier = modifier)
        }
    }
}