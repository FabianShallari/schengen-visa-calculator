package com.fabian.schengenvisacalculator.ui.screens.overview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.schengenvisacalculator.R
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import com.fabian.schengenvisacalculator.util.periodInDays
import com.fabian.schengenvisacalculator.util.quantityStringResource
import java.time.LocalDate

private const val DEGREES_PER_DAY = 360f / 90f
private const val START_DEGREE = 0f
private val STROKE_WIDTH_DP = 22.dp
internal val DAYS_INDICATOR_SIZE_DP = 180.dp

@Composable
fun DaysIndicator(modifier: Modifier = Modifier, completedDateRanges: List<LocalDateRange>) {
    val daysCompleted = completedDateRanges.fold(0L) { accumulator, dateRange ->
        accumulator + dateRange.periodInDays(includeLastDate = true)
    }

    val daysLeft = (90 - daysCompleted).coerceIn(0, 90).toInt()

    Box(
        modifier = modifier.preferredSize(DAYS_INDICATOR_SIZE_DP),
        contentAlignment = Alignment.Center
    ) {
        val strokeColor = MaterialTheme.colors.primary

        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(STROKE_WIDTH_DP / 2)) {
            val (centerX, centerY) = size.width / 2 to size.height / 2
            val diameter = minOf(size.height, size.width)
            val radius = diameter / 2

            translate(left = centerX - radius, top = centerY - radius) {
                // Draw the arc on the bottom which represents all 180 days
                drawArc(
                    color = strokeColor.copy(alpha = 0.25f),
                    startAngle = 0f.toDegrees(),
                    sweepAngle = 360f,
                    useCenter = false,
                    size = Size(diameter, diameter),
                    style = Stroke(width = STROKE_WIDTH_DP.toPx(), cap = StrokeCap.Round)
                )
                // Draw the arc on the top which represents days completed
                drawArc(
                    color = strokeColor,
                    startAngle = START_DEGREE.toDegrees(),
                    sweepAngle = daysCompleted * DEGREES_PER_DAY,
                    useCenter = false,
                    size = Size(diameter, diameter),
                    style = Stroke(width = STROKE_WIDTH_DP.toPx(), cap = StrokeCap.Round)
                )
            }
        }
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = daysLeft.toString(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Text(
                text = quantityStringResource(id = R.plurals.days_left, quantity = daysLeft),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }

    }
}

/**
 * Canvas starts drawing arcs from 3 o'clock
 *
 * We want to start drawing at 12 o'clock, therefore we shift the start angle by -90°
 */
private fun Float.toDegrees(): Float = this - 90f


@Composable
private fun DaysIndicatorPreview(darkTheme: Boolean) {
    SchengenCalculatorTheme(darkTheme = darkTheme) {
        Surface {
            DaysIndicator(
                completedDateRanges = listOf(
                    LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8)
                )
            )
        }
    }
}

@Preview("Days Indicator - Dark", showBackground = true)
@Composable
private fun DaysIndicatorDarkPreview() {
    DaysIndicatorPreview(darkTheme = true)
}

@Preview("DaysIndicator - Light", showBackground = true)
@Composable
private fun DaysIndicatorLightPreview() {
    DaysIndicatorPreview(darkTheme = false)
}