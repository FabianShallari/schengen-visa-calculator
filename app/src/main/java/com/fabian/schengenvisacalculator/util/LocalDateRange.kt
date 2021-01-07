package com.fabian.schengenvisacalculator.util

import java.time.LocalDate

typealias LocalDateRange = ClosedRange<LocalDate>

fun LocalDateRange.periodInDays(includeLastDate: Boolean = false): Long = if (includeLastDate) {
    start.periodInDays(toDateExclusive = endInclusive) + 1
} else {
    start.periodInDays(toDateExclusive = endInclusive)
}

enum class PositionOfDateInsideRange {
    Start, End, Middle, Whole, Out
}

fun LocalDateRange.positionOf(date: LocalDate): PositionOfDateInsideRange = when (date) {
    in this -> {
        when {
            date.isEqual(start) && date.isEqual(endInclusive) -> {
                PositionOfDateInsideRange.Whole
            }
            date.isEqual(start) -> {
                PositionOfDateInsideRange.Start
            }
            date.isEqual(endInclusive) -> {
                PositionOfDateInsideRange.End
            }
            else -> {
                PositionOfDateInsideRange.Middle
            }
        }
    }
    else -> PositionOfDateInsideRange.Out
}

fun List<LocalDateRange>.positionOf(date: LocalDate): PositionOfDateInsideRange {
    for (dateRange in this) {
        val positionInRange = dateRange.positionOf(date)
        if (positionInRange != PositionOfDateInsideRange.Out) {
            return positionInRange
        }
    }

    return PositionOfDateInsideRange.Out
}