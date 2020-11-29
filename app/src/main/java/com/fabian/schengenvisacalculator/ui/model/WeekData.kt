package com.fabian.schengenvisacalculator.ui.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class WeekData(
    val firstDateOfWeek: LocalDate,
    val lastDateOfWeek: LocalDate,
    val isFirstWeekOfMonth: Boolean,
    val endDaysToSkip: Int,
    val startDaysToSkip: Int,
) {
    val daysInWeek: Int
        get() = 7 - (startDaysToSkip + endDaysToSkip)
}