package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fabian.schengenvisacalculator.ui.navigation.BottomNavScreen
import com.fabian.schengenvisacalculator.ui.navigation.startScreen
import com.fabian.schengenvisacalculator.ui.providers.AmbientNavigator
import com.fabian.schengenvisacalculator.ui.screens.calendar.Calendar
import com.fabian.schengenvisacalculator.ui.screens.info.Info
import com.fabian.schengenvisacalculator.ui.screens.overview.Overview
import com.fabian.schengenvisacalculator.util.rangeOf
import java.time.LocalDate

// TODO: 7.1.21 Move these into viewModel
private val completedDateRanges = listOf(
    rangeOf(
        startInclusive = LocalDate.of(2020, 1, 12),
        endInclusive = LocalDate.of(2020, 1, 12)),
    rangeOf(
        startInclusive = LocalDate.of(2020, 2, 1),
        endInclusive = LocalDate.of(2020, 2, 16)
    ),
    rangeOf(
        startInclusive = LocalDate.of(2020, 2, 24),
        endInclusive = LocalDate.of(2020, 3, 5)
    )
)

private val calendarDateRange = rangeOf(
    startInclusive = LocalDate.of(2020, 1, 12),
    endInclusive = LocalDate.of(2020, 12, 31))

@Composable
fun BottomBarScreensHost(modifier: Modifier = Modifier) {
    val navController = AmbientNavigator.current

    NavHost(navController, startDestination = startScreen.route) {
        composable(BottomNavScreen.Calendar.route) {
            Calendar(
                modifier = modifier,
                calendarDateRange = calendarDateRange,
                selectedDateRanges = completedDateRanges
            )
        }
        composable(BottomNavScreen.Info.route) {
            Info(modifier = modifier)
        }
        composable(BottomNavScreen.Overview.route) {
            Overview(modifier = modifier, completedDateRanges = completedDateRanges)
        }
    }
}
