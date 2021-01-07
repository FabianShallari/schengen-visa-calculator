package com.fabian.schengenvisacalculator.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.fabian.schengenvisacalculator.ui.providers.ContentAlphaProvider
import java.time.DayOfWeek

private fun Modifier.clipNonNull(shape: Shape?) =
    composed { shape?.let { this.clip(it) } ?: Modifier }

private fun Modifier.clickableIf(isClickable: Boolean, onClick: () -> Unit) =
    composed { if (isClickable) this.clickable(onClick = onClick) else Modifier }

private fun Modifier.backgroundColorNonNull(backgroundColor: Color?) =
    composed { backgroundColor?.let { background(color = it) } ?: Modifier }

@Composable
fun CalendarDayOfMonthCell(cellState: CalendarCellState, dayOfMonth: Int) {
    CalendarCell(modifier = Modifier
        .clipNonNull(cellState.clipShape())
        .backgroundColorNonNull(cellState.backgroundColor())
        .clickableIf(isClickable = cellState.isClickable(), onClick = {})
    ) {
        Text(
            text = dayOfMonth.toString(),
            style = MaterialTheme.typography.body2.copy(color = cellState.textColor()),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
    }
}

@Composable
fun CalendarEmptyCell() {
    CalendarCell()
}

@Composable
fun DayOfWeekCell(dayOfWeek: DayOfWeek) {
    CalendarCell {
        ContentAlphaProvider(contentAlpha = ContentAlpha.disabled) {
            Text(
                text = dayOfWeek.name[0].toString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
            )
        }
    }
}

@Composable
private fun CalendarCell(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = emptyContent(),
) {
    Box(
        modifier = modifier.preferredSize(CELL_SIZE_DP)
    ) {
        content()
    }
}
