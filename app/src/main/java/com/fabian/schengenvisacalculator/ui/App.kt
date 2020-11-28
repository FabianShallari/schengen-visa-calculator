package com.fabian.schengenvisacalculator.ui

import androidx.compose.runtime.Composable
import com.fabian.schengenvisacalculator.ui.providers.NavControllerProvider
import com.fabian.schengenvisacalculator.ui.providers.StartDayOfWeekProvider
import com.fabian.schengenvisacalculator.ui.screens.Home
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme

@Composable
fun App() {
    SchengenCalculatorTheme {
        NavControllerProvider {
            StartDayOfWeekProvider {
                Home()
            }
        }
    }
}