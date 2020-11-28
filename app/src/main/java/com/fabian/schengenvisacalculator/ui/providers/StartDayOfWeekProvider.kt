package com.fabian.schengenvisacalculator.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import com.fabian.schengenvisacalculator.data.SettingsRepository
import java.time.DayOfWeek

val StartDayOfWeekAmbient = ambientOf { DayOfWeek.SUNDAY }

@Composable
fun StartDayOfWeekProvider(children: @Composable () -> Unit) {
    val startDayOfWeek = SettingsRepository().startDayOfWeek

    Providers(StartDayOfWeekAmbient provides startDayOfWeek) {
        children()
    }
}