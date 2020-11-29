package com.fabian.schengenvisacalculator.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit

typealias LocalDateRange = ClosedRange<LocalDate>

fun Collection<LocalDate>.earliest(): LocalDate {
    if (this.isEmpty()) {
        throw IllegalStateException("Collection of dates must not be empty")
    }

    return this.minOrNull()!!
}

fun LocalDateRange.periodInDays(includeLastDate: Boolean = false): Long = if (includeLastDate) {
    start.periodInDays(toDateExclusive = endInclusive) + 1
} else {
    start.periodInDays(toDateExclusive = endInclusive)
}

fun LocalDate.periodInDays(toDateExclusive: LocalDate): Long =
    ChronoUnit.DAYS.between(this, toDateExclusive)

fun LocalDate.isBeforeOrEqual(other: LocalDate): Boolean =
    this.isBefore(other) || this.isEqual(other)

enum class PositionInDateRange {
    Start, End, Middle, Whole, Out
}

fun LocalDateRange.positionOf(date: LocalDate): PositionInDateRange = when (date) {
    in this -> {
        when {
            date.isEqual(start) && date.isEqual(endInclusive) -> {
                PositionInDateRange.Whole
            }
            date.isEqual(start) -> {
                PositionInDateRange.Start
            }
            date.isEqual(endInclusive) -> {
                PositionInDateRange.End
            }
            else -> {
                PositionInDateRange.Middle
            }
        }
    }
    else -> PositionInDateRange.Out
}

fun List<LocalDateRange>.positionOf(date: LocalDate): PositionInDateRange {
    for (dateRange in this) {
        val positionInRange = dateRange.positionOf(date)
        if (positionInRange != PositionInDateRange.Out) {
            return positionInRange
        }
    }

    return PositionInDateRange.Out
}