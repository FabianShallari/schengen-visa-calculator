package com.fabian.schengenvisacalculator.util

import java.time.LocalDate
import java.time.Period

typealias LocalDateRange = ClosedRange<LocalDate>

fun Collection<LocalDate>.earliest(): LocalDate {
    if (this.isEmpty()) {
        throw IllegalStateException("Collection of dates must not be empty")
    }

    return this.minOrNull()!!
}

fun LocalDateRange.periodInDays(includeLastDate: Boolean = false): Int = if (includeLastDate) {
    start.periodInDays(toDateExclusive = endInclusive) + 1
} else {
    start.periodInDays(toDateExclusive = endInclusive)
}

fun LocalDate.periodInDays(toDateExclusive: LocalDate): Int =
    Period.between(this, toDateExclusive).days

fun LocalDate.isBeforeOrEqual(other: LocalDate): Boolean = this.isBefore(other) || this.isEqual(other)



