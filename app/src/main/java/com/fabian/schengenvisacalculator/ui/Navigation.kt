package com.fabian.schengenvisacalculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticAmbientOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fabian.schengenvisacalculator.ui.screens.Calendar
import java.time.LocalDate

val NavControllerAmbient =
    staticAmbientOf<NavHostController> { error("NavController not provided") }

sealed class Screens(val route: String) {
    object Home : Screens(route = "home")
    object Info : Screens(route = "info")
}

@Composable
fun NavigationHost() {
    val navController = NavControllerAmbient.current
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            Calendar(
                calendarDateRange = LocalDate.of(2020, 1, 12)..LocalDate.of(2020, 12, 31),
                selectedDateRanges = listOf()
            )
        }
    }
}