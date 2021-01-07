package com.fabian.schengenvisacalculator.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.remember
import com.fabian.schengenvisacalculator.data.SettingsRepository
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

// TODO: 11/30/20 Move these into observable values so they can be served from a view model
val AmbientStartDayOfWeek = ambientOf { DayOfWeek.MONDAY }
val AmbientDateTimeFormatter = ambientOf { DateTimeFormatter.ofPattern("dd MMM yyyy") }

@Composable
fun SettingsProvider(content: @Composable () -> Unit) {
    val settingsRepository = remember { SettingsRepository() }

    Providers(
        AmbientStartDayOfWeek provides settingsRepository.startDayOfWeek,
        AmbientDateTimeFormatter provides settingsRepository.dateTimeFormatter
    ) {
        content()
    }
}
