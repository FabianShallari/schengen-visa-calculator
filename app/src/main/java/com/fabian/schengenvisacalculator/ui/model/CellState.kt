package com.fabian.schengenvisacalculator.ui.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

enum class CellState {
    NotDate,
    EmptySlot,
    SelectableDate,
    DisabledDate,
    SelectedStartOfRange,
    DisabledStartOfRange,
    SelectedMidRange,
    SelectedWholeRange,
    DisabledMidRange,
    SelectedEndOfRange,
    DisabledEndOfRange,
    CurrentSelection
}

@Composable
val CellState.cellModifier: Modifier
    get() = when (this) {
        CellState.NotDate,
        CellState.DisabledDate,
        CellState.EmptySlot -> Modifier
        CellState.SelectableDate -> Modifier
            .clip(CircleShape)
            .clickable(onClick = {})
        CellState.SelectedWholeRange -> Modifier.clip(CircleShape)
            .clickable(onClick = {})
            .background(color = MaterialTheme.colors.primaryVariant)
        CellState.CurrentSelection -> Modifier
            .clip(RoundedCornerShape(topLeftPercent = 50, bottomLeftPercent = 50))
            .clickable(onClick = {})
            .background(color = MaterialTheme.colors.primaryVariant)
        CellState.SelectedStartOfRange -> Modifier
            .clip(RoundedCornerShape(topLeftPercent = 50, bottomLeftPercent = 50))
            .clickable(onClick = {})
            .background(color = MaterialTheme.colors.primaryVariant)
        CellState.DisabledStartOfRange -> Modifier
            .clip(RoundedCornerShape(topLeftPercent = 50, bottomLeftPercent = 50))
            .background(color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f))
        CellState.SelectedMidRange -> Modifier
            .clickable(onClick = {})
            .background(color = MaterialTheme.colors.primaryVariant)
        CellState.DisabledMidRange -> Modifier
            .background(color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f))
        CellState.SelectedEndOfRange -> Modifier
            .clip(RoundedCornerShape(topRightPercent = 50, bottomRightPercent = 50))
            .clickable(onClick = {})
            .background(color = MaterialTheme.colors.primaryVariant)
        CellState.DisabledEndOfRange -> Modifier
            .clip(RoundedCornerShape(topRightPercent = 50, bottomRightPercent = 50))
            .background(color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f))
    }

@Composable
val CellState.textModifier: Modifier
    get() = when (this) {
        CellState.NotDate -> Modifier
        CellState.EmptySlot -> Modifier
        CellState.SelectableDate -> Modifier
        CellState.DisabledDate -> Modifier
        CellState.SelectedStartOfRange -> Modifier
        CellState.DisabledStartOfRange -> Modifier
        CellState.SelectedEndOfRange -> Modifier
        CellState.DisabledEndOfRange -> Modifier
        CellState.SelectedMidRange -> Modifier
        CellState.DisabledMidRange -> Modifier
        CellState.CurrentSelection -> Modifier
        CellState.SelectedWholeRange -> Modifier
    }
