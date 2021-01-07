package com.fabian.schengenvisacalculator.ui.screens.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.schengenvisacalculator.ui.model.WeekData
import com.fabian.schengenvisacalculator.ui.providers.AmbientStartDayOfWeek
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import com.fabian.schengenvisacalculator.util.PositionOfDateInsideRange
import com.fabian.schengenvisacalculator.util.positionOf
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarWeekItem(
    index: Int,
    selectedDateRanges: List<LocalDateRange>,
    weekData: WeekData,
) {
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

    CalendarWeek(selectedDateRanges = selectedDateRanges, weekData = weekData)
}

@Composable
private fun CalendarWeek(
    modifier: Modifier = Modifier,
    selectedDateRanges: List<LocalDateRange>,
    weekData: WeekData,
) {
    Row(
        modifier = modifier
            .padding(vertical = 1.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        // Render empty cells for start days to skip
        for (day in 0 until weekData.startDaysToSkip) {
            CalendarEmptyCell()
        }

        // Render date cells
        for (day in 0 until weekData.daysInWeek) {
            val currentYear = weekData.firstDateOfWeek.year
            val currentMonth = weekData.firstDateOfWeek.monthValue
            val currentDayOfMonth = weekData.firstDateOfWeek.dayOfMonth + day

            // memoize the current date so it doesn't get allocated on each composition
            val currentDate = remember(currentYear, currentMonth, currentDayOfMonth) {
                LocalDate.of(
                    currentYear,
                    currentMonth,
                    currentDayOfMonth
                )
            }

            val cellState =
                when (selectedDateRanges.positionOf(currentDate)) {
                    PositionOfDateInsideRange.Out -> CalendarCellState.SelectableDate
                    PositionOfDateInsideRange.Start -> CalendarCellState.SelectedStartOfRange
                    PositionOfDateInsideRange.Whole -> CalendarCellState.SelectedWholeRange
                    PositionOfDateInsideRange.Middle -> CalendarCellState.SelectedMidRange
                    PositionOfDateInsideRange.End -> CalendarCellState.SelectedEndOfRange
                }

            CalendarDayOfMonthCell(dayOfMonth = currentDayOfMonth, cellState = cellState)
        }

        // Render empty cells for end days to skip
        for (day in 0 until weekData.endDaysToSkip) {
            CalendarEmptyCell()
        }
    }
}

@Composable
fun MonthHeader(modifier: Modifier = Modifier, month: YearMonth) {
    Row(
        modifier = modifier
            .wrapContentHeight(Alignment.Bottom)
            .preferredWidth((CELL_SIZE_DP * 7) + (12 * 2).dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.align(Alignment.Bottom),
            text = month.month.name,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.align(Alignment.Bottom),
            text = "${month.year}",
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun WeekHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(),
    ) {
        val calendarStartDayOfWeek = AmbientStartDayOfWeek.current

        for (weekDay in 0 until 7L) {
            val currentDayOfWeek = calendarStartDayOfWeek.plus(weekDay)
            DayOfWeekCell(dayOfWeek = currentDayOfWeek)
        }
    }
}

@Composable
private fun WeekPreview(darkTheme: Boolean) {
    SchengenCalculatorTheme(darkTheme = darkTheme) {
        Surface {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CalendarWeekItem(
                    index = 0,
                    selectedDateRanges = listOf(
                        LocalDate.of(2020, 3, 2)..LocalDate.of(2020, 3, 5)
                    ),
                    weekData = WeekData(
                        firstDateOfWeek = LocalDate.of(2020, 3, 1),
                        lastDateOfWeek = LocalDate.of(2020, 3, 7),
                        isFirstWeekOfMonth = true,
                        startDaysToSkip = 0,
                        endDaysToSkip = 0
                    )
                )
            }
        }
    }
}

@Preview("Week Item Preview - Dark", showBackground = true)
@Composable
private fun WeekItemPreviewDark() {
    WeekPreview(darkTheme = true)
}

@Preview("Week Item Preview - Light", showBackground = true)
@Composable
private fun WeekItemPreviewLight() {
    WeekPreview(darkTheme = false)
}




