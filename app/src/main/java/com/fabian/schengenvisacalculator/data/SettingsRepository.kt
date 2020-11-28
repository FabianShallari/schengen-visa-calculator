package com.fabian.schengenvisacalculator.data

import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.NightMode
import java.time.DayOfWeek


class SettingsRepository {

    @NightMode
    val nightMode: Int
        get() = MODE_NIGHT_FOLLOW_SYSTEM

    val startDayOfWeek: DayOfWeek
        get() = DayOfWeek.MONDAY
}