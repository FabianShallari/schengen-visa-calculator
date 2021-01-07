package com.fabian.schengenvisacalculator.ui.screens.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.schengenvisacalculator.ui.model.WeekData
import com.fabian.schengenvisacalculator.ui.providers.AmbientStartDayOfWeek
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame

val CELL_SIZE_DP = 48.dp

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarDateRange: LocalDateRange,
    selectedDateRanges: List<LocalDateRange>,
) {

    val weeksData = calculateWeeksData(
        calendarDateRange = calendarDateRange,
        startDayOfWeek = AmbientStartDayOfWeek.current
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = weeksData) { index, item ->
                CalendarWeekItem(
                    index = index,
                    weekData = item,
                    selectedDateRanges = selectedDateRanges
                )
            }
        }
    }

}

// TODO: 12/4/20 This should be calculated on a background thread and be part of a ViewModel
private fun calculateWeeksData(
    calendarDateRange: LocalDateRange,
    startDayOfWeek: DayOfWeek,
): List<WeekData> {
    val endDayOfWeek = startDayOfWeek.minus(1)

    val weeksData = mutableListOf<WeekData>()
    var startDateOfWeek = calendarDateRange.start

    while (startDateOfWeek.isBeforeOrEqual(calendarDateRange.endInclusive)) {
        val dateAtEndOfWeek = startDateOfWeek.with(nextOrSame(endDayOfWeek))
        val dateAtEndOfMonth =
            YearMonth.of(startDateOfWeek.year, startDateOfWeek.month).atEndOfMonth()
        val dateAtEndOfDateRange = calendarDateRange.endInclusive

        // Find end date of this week
        // Earliest of: (day at end of week || day at end of month || day at end of date range)
        val endDateOfWeek =
            listOf(dateAtEndOfWeek, dateAtEndOfMonth, dateAtEndOfDateRange).earliest()

        val dayAtStartOfWeek = startDateOfWeek.with(previousOrSame(startDayOfWeek))
        val dayAtEndOfWeek = endDateOfWeek.with(nextOrSame(endDayOfWeek))

        weeksData.add(
            WeekData(
                firstDateOfWeek = startDateOfWeek,
                lastDateOfWeek = endDateOfWeek,
                isFirstWeekOfMonth = startDateOfWeek.dayOfMonth == 1 || startDateOfWeek.isEqual(
                    calendarDateRange.start
                ),
                startDaysToSkip = dayAtStartOfWeek.periodInDays(startDateOfWeek).toInt(),
                endDaysToSkip = endDateOfWeek.periodInDays(dayAtEndOfWeek).toInt(),
            )
        )

        // start on next week
        startDateOfWeek = endDateOfWeek.plusDays(1)
    }

    return weeksData
}

@Composable
private fun CalendarPreview(
    darkTheme: Boolean,
) {
    SchengenCalculatorTheme(darkTheme = darkTheme) {
        Providers(AmbientStartDayOfWeek provides DayOfWeek.MONDAY) {
            Calendar(
                calendarDateRange = rangeOf(
                    startInclusive = LocalDate.of(2020, 1, 12),
                    endInclusive = LocalDate.of(2020, 3, 21)),

                selectedDateRanges = listOf(
                    rangeOf(
                        startInclusive = LocalDate.of(2020, 1, 12),
                        endInclusive = LocalDate.of(2020, 1, 12)),
                    rangeOf(
                        startInclusive = LocalDate.of(2020, 2, 1),
                        endInclusive = LocalDate.of(2020, 2, 16)
                    ),
                    rangeOf(
                        startInclusive = LocalDate.of(2020, 2, 24),
                        endInclusive = LocalDate.of(2020, 3, 5)
                    )
                )
            )
        }
    }
}

@Preview("CalendarPreview - Dark", showBackground = true)
@Composable
private fun CalendarPreviewDark() {
    CalendarPreview(darkTheme = true)
}

@Preview("CalendarPreview - Light", showBackground = true)
@Composable
private fun CalendarPreviewLight() {
    CalendarPreview(darkTheme = false)
}