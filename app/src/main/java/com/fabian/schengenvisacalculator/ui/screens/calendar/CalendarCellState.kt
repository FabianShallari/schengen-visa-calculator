package com.fabian.schengenvisacalculator.ui.screens.calendar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientContentColor
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

enum class CalendarCellState {
    DisabledDate,
    SelectableDate,
    SelectedStartOfRange,
    DisabledStartOfRange,
    SelectedMidRange,
    DisabledMidRange,
    SelectedWholeRange,
    DisabledWholeRange,
    SelectedEndOfRange,
    DisabledEndOfRange,
    CurrentSelection
}


@Composable
fun CalendarCellState.clipShape(): Shape? = when (this) {
    CalendarCellState.DisabledDate -> CircleShape
    CalendarCellState.SelectableDate -> CircleShape
    CalendarCellState.CurrentSelection -> CircleShape
    CalendarCellState.SelectedStartOfRange -> RoundedCornerShape(
        topLeftPercent = 50,
        bottomLeftPercent = 50
    )
    CalendarCellState.DisabledStartOfRange -> RoundedCornerShape(
        topLeftPercent = 50,
        bottomLeftPercent = 50
    )
    CalendarCellState.SelectedMidRange -> null
    CalendarCellState.DisabledMidRange -> null
    CalendarCellState.SelectedWholeRange -> CircleShape
    CalendarCellState.DisabledWholeRange -> CircleShape
    CalendarCellState.SelectedEndOfRange -> RoundedCornerShape(
        topRightPercent = 50,
        bottomRightPercent = 50
    )
    CalendarCellState.DisabledEndOfRange -> RoundedCornerShape(
        topRightPercent = 50,
        bottomRightPercent = 50
    )
}

@Composable
fun CalendarCellState.backgroundColor(): Color? = when (this) {
    // Disabled or unselected
    CalendarCellState.DisabledDate,
    CalendarCellState.SelectableDate,
    -> null

    // Enabled and selected
    CalendarCellState.CurrentSelection,
    CalendarCellState.SelectedWholeRange,
    CalendarCellState.SelectedStartOfRange,
    CalendarCellState.SelectedEndOfRange,
    CalendarCellState.SelectedMidRange,
    -> MaterialTheme.colors.primaryVariant

    // Disabled And Selected
    CalendarCellState.DisabledStartOfRange,
    CalendarCellState.DisabledMidRange,
    CalendarCellState.DisabledWholeRange,
    CalendarCellState.DisabledEndOfRange,
    -> MaterialTheme.colors.primaryVariant.copy(alpha = ContentAlpha.disabled)
}

@Composable
fun CalendarCellState.textColor(): Color = when (this) {
    // Disabled or unselected
    CalendarCellState.DisabledDate -> AmbientContentColor.current.copy(
        alpha = ContentAlpha.disabled
    )
    CalendarCellState.SelectableDate -> AmbientContentColor.current

    // Enabled and selected
    CalendarCellState.CurrentSelection,
    CalendarCellState.SelectedWholeRange,
    CalendarCellState.SelectedStartOfRange,
    CalendarCellState.SelectedEndOfRange,
    CalendarCellState.SelectedMidRange,
    -> contentColorFor(color = MaterialTheme.colors.primaryVariant)

    // Disabled And Selected
    CalendarCellState.DisabledStartOfRange,
    CalendarCellState.DisabledMidRange,
    CalendarCellState.DisabledWholeRange,
    CalendarCellState.DisabledEndOfRange,
    -> contentColorFor(color = MaterialTheme.colors.primaryVariant).copy(
        alpha = ContentAlpha.disabled
    )
}

fun CalendarCellState.isClickable(): Boolean = when (this) {
    CalendarCellState.DisabledDate,
    CalendarCellState.DisabledStartOfRange,
    CalendarCellState.DisabledMidRange,
    CalendarCellState.DisabledWholeRange,
    CalendarCellState.DisabledEndOfRange,
    -> false

    CalendarCellState.SelectableDate,
    CalendarCellState.SelectedStartOfRange,
    CalendarCellState.SelectedMidRange,
    CalendarCellState.SelectedWholeRange,
    CalendarCellState.SelectedEndOfRange,
    CalendarCellState.CurrentSelection,
    -> true
}
