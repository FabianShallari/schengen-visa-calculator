package com.fabian.schengenvisacalculator.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.ui.graphics.vector.VectorAsset
import com.fabian.schengenvisacalculator.R

sealed class Screen(val route: String, val iconAsset: VectorAsset, @StringRes val label: Int) {
    object DaysIndicator :
        Screen(route = "days_indicator", iconAsset = Icons.Sharp.Home, label = R.string.home)

    object Calendar :
        Screen(route = "calendar", iconAsset = Icons.Sharp.DateRange, label = R.string.calendar)

    object Info : Screen(route = "info", iconAsset = Icons.Sharp.Info, label = R.string.info)
}

val screens = listOf(Screen.DaysIndicator, Screen.Calendar, Screen.Info)
val startDestination = Screen.DaysIndicator