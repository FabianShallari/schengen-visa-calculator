package com.fabian.schengenvisacalculator.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.fabian.schengenvisacalculator.R

interface Screen {
    val route: String
}

sealed class BottomNavScreen(
    override val route: String,
    val imageVector: ImageVector,
    @StringRes val label: Int,
) : Screen {

    object Overview :
        BottomNavScreen(
            route = "overview",
            imageVector = Icons.Sharp.Home,
            label = R.string.home)

    object Calendar :
        BottomNavScreen(
            route = "calendar",
            imageVector = Icons.Sharp.DateRange,
            label = R.string.calendar)

    object Info :
        BottomNavScreen(
            route = "info",
            imageVector = Icons.Sharp.Info,
            label = R.string.info)
}

val bottomNavScreens: List<BottomNavScreen> =
    listOf(BottomNavScreen.Overview, BottomNavScreen.Calendar, BottomNavScreen.Info)

val startScreen: Screen = BottomNavScreen.Overview
