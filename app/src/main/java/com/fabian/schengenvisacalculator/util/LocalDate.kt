package com.fabian.schengenvisacalculator.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun Collection<LocalDate>.earliest(): LocalDate {
    if (this.isEmpty()) {
        throw IllegalStateException("Collection of dates must not be empty")
    }

    return this.minOrNull()!!
}

fun LocalDate.periodInDays(toDateExclusive: LocalDate): Long =
    ChronoUnit.DAYS.between(this, toDateExclusive)

fun LocalDate.isBeforeOrEqual(other: LocalDate): Boolean =
    this.isBefore(other) || this.isEqual(other)