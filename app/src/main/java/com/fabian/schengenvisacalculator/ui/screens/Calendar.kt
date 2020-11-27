package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.ui.model.CellState
import com.fabian.schengenvisacalculator.ui.model.WeekData
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters.*
import kotlin.math.abs

val CalendarStartDayOfWeek = ambientOf { DayOfWeek.SUNDAY }

fun Collection<LocalDate>.earliest(): LocalDate {
    if (this.isEmpty()) {
        throw IllegalStateException("Collection of dates must not be empty")
    }

    return this.minOrNull()!!
}

@Composable
fun Calendar(
    calendarDateRange: ClosedRange<LocalDate>,
    selectedDateRanges: List<ClosedRange<LocalDate>>
) {

    val startDayOfWeek = CalendarStartDayOfWeek.current
    val endDayOfWeek = startDayOfWeek.minus(1)

    val weeksData = mutableListOf<WeekData>()
    var startDateOfWeek = calendarDateRange.start


    while (startDateOfWeek.isBefore(calendarDateRange.endInclusive) ||
        startDateOfWeek.isEqual(calendarDateRange.endInclusive)
    ) {
        val dateAtEndOfWeek = startDateOfWeek.with(next(endDayOfWeek))
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
                isFirstWeekOfMonth = startDateOfWeek.dayOfMonth == 1 || startDateOfWeek.isEqual(
                    calendarDateRange.start
                ),
                startDaysToSkip = abs(Period.between(dayAtStartOfWeek, startDateOfWeek).days),
                endDaysToSkip = abs(Period.between(endDateOfWeek, dayAtEndOfWeek).days),
            )
        )

        // start on next week
        startDateOfWeek = endDateOfWeek.plusDays(1)
    }

    Providers(CalendarStartDayOfWeek provides startDayOfWeek) {
        Surface(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            LazyColumnFor(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                items = weeksData
            ) { weekData ->
                if (weekData.isFirstWeekOfMonth) {
                    MonthHeader(
                        month = YearMonth.of(
                            weekData.firstDateOfWeek.year,
                            weekData.firstDateOfWeek.month
                        )
                    )

                    WeekHeader()
                }

                Week(weekData = weekData)
            }
        }
    }
}

@Composable
private fun MonthHeader(modifier: Modifier = Modifier, month: YearMonth) {
    Row(modifier = modifier.padding(horizontal = 12.dp).padding(top = 24.dp, bottom = 8.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = month.month.name,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${month.year}",
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun WeekHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().wrapContentWidth(),
    ) {
        val calendarStartDayOfWeek = CalendarStartDayOfWeek.current

        for (weekDay in 0 until 7L) {
            val currentDayOfWeek = calendarStartDayOfWeek.plus(weekDay)

            CellContainer(
                state = CellState.NotSelectable
            ) {
                Text(
                    text = currentDayOfWeek.name[0].toString(),
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onBackground.copy(
                            alpha = 0.4f
                        )
                    ),
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                )
            }
        }
    }
}

@Composable
private fun Week(
    modifier: Modifier = Modifier,
    weekData: WeekData,
) {
    Row(modifier = modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)) {
        // Render empty cells for start days to skip
        for (day in 0 until weekData.startDaysToSkip) {
            CellContainer(state = CellState.NotSelectable) {}
        }

        // Render date cells
        for (day in 0 until weekData.daysInWeek) {
            CellContainer(state = CellState.NotSelectable) {
                Text(
                    text = (weekData.firstDateOfWeek.dayOfMonth + day).toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                )
            }
        }

        // Render empty cells for end days to skip
        for (day in 0 until weekData.endDaysToSkip) {
            CellContainer(state = CellState.NotSelectable) {}
        }
    }
}

@Composable
private fun CellContainer(
    modifier: Modifier = Modifier,
    state: CellState,
    children: @Composable () -> Unit
) {
    Box(
        modifier = modifier.preferredSize(48.dp).clip(CircleShape)
    ) {
        children()
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
    SchengenCalculatorTheme(darkTheme = false) {
        Calendar(
            calendarDateRange = LocalDate.of(2020, 1, 12)..LocalDate.of(2020, 12, 21),
            selectedDateRanges = listOf()
        )
    }
}