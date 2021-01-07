package com.fabian.schengenvisacalculator.ui

import androidx.compose.runtime.Composable
import com.fabian.schengenvisacalculator.ui.providers.NavigatorProvider
import com.fabian.schengenvisacalculator.ui.providers.SettingsProvider
import com.fabian.schengenvisacalculator.ui.screens.Home
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme

@Composable
fun App() {
    SchengenCalculatorTheme {
        SettingsProvider {
            NavigatorProvider {
                Home()
            }
        }
    }
}