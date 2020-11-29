package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.ui.model.CellState
import com.fabian.schengenvisacalculator.ui.model.WeekData
import com.fabian.schengenvisacalculator.ui.providers.StartDayOfWeekAmbient
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import com.fabian.schengenvisacalculator.util.earliest
import com.fabian.schengenvisacalculator.util.isBeforeOrEqual
import com.fabian.schengenvisacalculator.util.periodInDays
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters.*

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarDateRange: LocalDateRange,
    selectedDateRanges: List<LocalDateRange>
) {

    val startDayOfWeek = StartDayOfWeekAmbient.current
    val endDayOfWeek = startDayOfWeek.minus(1)

    val weeksData = mutableListOf<WeekData>()
    var startDateOfWeek = calendarDateRange.start


    while (startDateOfWeek.isBeforeOrEqual(calendarDateRange.endInclusive)) {
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
                startDaysToSkip = dayAtStartOfWeek.periodInDays(startDateOfWeek),
                endDaysToSkip = endDateOfWeek.periodInDays(dayAtEndOfWeek),
            )
        )

        // start on next week
        startDateOfWeek = endDateOfWeek.plusDays(1)
    }


    Surface(
        modifier = modifier.fillMaxWidth().fillMaxHeight()
    ) {
        LazyColumnForIndexed(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            items = weeksData,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { index, weekData ->
            if (weekData.isFirstWeekOfMonth) {
                MonthHeader(
                    modifier = Modifier.padding(
                        top = if (index == 0) 8.dp else 24.dp,
                        bottom = 8.dp
                    ),
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

@Composable
private fun MonthHeader(modifier: Modifier = Modifier, month: YearMonth) {
    Row(
        modifier = modifier
            .wrapContentHeight(Alignment.Bottom)
            .preferredWidth((7 * 48).dp + (12 * 2).dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.Bottom),
            text = month.month.name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.Bottom),
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
        val calendarStartDayOfWeek = StartDayOfWeekAmbient.current

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
            CellContainer(state = CellState.NotSelectable)
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
            CellContainer(state = CellState.NotSelectable)
        }
    }
}

@Composable
private fun CellContainer(
    modifier: Modifier = Modifier,
    state: CellState,
    children: @Composable () -> Unit = emptyContent()
) {
    Box(
        modifier = modifier.preferredSize(48.dp).clip(CircleShape)
    ) {
        children()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun CalendarPreview() {
    SchengenCalculatorTheme(darkTheme = false) {
        Calendar(
            calendarDateRange = LocalDate.of(2020, 1, 12)..LocalDate.of(2020, 3, 21),
            selectedDateRanges = listOf()
        )
    }
}