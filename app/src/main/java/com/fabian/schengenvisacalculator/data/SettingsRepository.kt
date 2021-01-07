package com.fabian.schengenvisacalculator.data

import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.NightMode
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

// TODO: 12/6/20 Use LocalDataStore for these settings
class SettingsRepository {

    @NightMode
    val nightMode: Int
        get() = MODE_NIGHT_FOLLOW_SYSTEM

    val startDayOfWeek: DayOfWeek
        get() = DayOfWeek.MONDAY

    val dateTimeFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofPattern("dd MMM yyyy")
    }
}