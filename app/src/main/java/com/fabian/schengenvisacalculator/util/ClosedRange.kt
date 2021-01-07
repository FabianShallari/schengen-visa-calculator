package com.fabian.schengenvisacalculator.util

fun <T : Comparable<T>> rangeOf(startInclusive: T, endInclusive: T): ClosedRange<T> =
    startInclusive.rangeTo(endInclusive)