package com.fabian.schengenvisacalculator.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.East
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.R
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import com.fabian.schengenvisacalculator.util.periodInDays
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val DEGREES_PER_DAY = 360f / 90f
private const val START_DEGREE = 0f
private val STROKE_WIDTH_DP = 22.dp
private val INDICATOR_SIZE_DP = 180.dp

/**
 * Canvas starts drawing arcs from 3 o'clock
 *
 * We want to start drawing at 12 o'clock, therefore we shift the start angle by -90°
 */
private fun Float.toDegrees(): Float = this - 90f

@Composable
fun CompletedDays(modifier: Modifier = Modifier, completedDatesList: List<LocalDateRange>) {
    CompletedDaysLayout(modifier = modifier) {
        DaysIndicator(
            completedDatesList = completedDatesList
        )
        CompletedDatesList(
            completedDatesList = completedDatesList
        )
    }
}

@Composable
fun CompletedDaysLayout(modifier: Modifier, children: @Composable () -> Unit) {
    WithConstraints(modifier = modifier) {
        if (constraints.maxWidth >= with(DensityAmbient.current) { 600.dp.toPx() }) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                children()
            }
        } else {
            Column(
                modifier = Modifier.padding(top = INDICATOR_SIZE_DP / 3).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                children()
            }
        }
    }
}

@Composable
fun DaysIndicator(modifier: Modifier = Modifier, completedDatesList: List<LocalDateRange>) {
    val daysCompleted = completedDatesList.fold(0L) { accumulator, dateRange ->
        accumulator + dateRange.periodInDays(includeLastDate = true)
    }

    val daysLeft = (90 - daysCompleted).coerceIn(0, 90)

    Box(modifier = modifier.preferredSize(INDICATOR_SIZE_DP), alignment = Alignment.Center) {
        val strokeColor = MaterialTheme.colors.primary

        Canvas(modifier = Modifier.fillMaxSize().padding(STROKE_WIDTH_DP / 2)) {
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
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = daysLeft.toString(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.days_left),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }

    }
}


@Composable
fun CompletedDatesList(
    modifier: Modifier = Modifier,
    completedDatesList: List<LocalDateRange>
) {
    LazyColumnFor(
        contentPadding = PaddingValues(
            all = 16.dp
        ),
        modifier = modifier.fillMaxHeight(),
        items = completedDatesList,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) { item -> CompletedDateRange(dateRange = item) }
}


@Composable
private fun CompletedDateRange(modifier: Modifier = Modifier, dateRange: LocalDateRange) {
    Card(
        modifier = modifier.padding(vertical = 6.dp).wrapContentSize(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.wrapContentSize().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = dateRange.start.format(DateTimeFormatter.ISO_DATE),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
                Icon(asset = Icons.Sharp.East)
                Text(
                    text = dateRange.endInclusive.format(DateTimeFormatter.ISO_DATE),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(
                        id = R.string.days,
                        dateRange.periodInDays(includeLastDate = true)
                    ),
                    style = MaterialTheme.typography.body2.copy(
                        color = contentColorFor(color = MaterialTheme.typography.body2.color).copy(
                            alpha = 0.7f
                        )
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CompletedDateRangePreview() {
    SchengenCalculatorTheme(darkTheme = true) {
        Surface {
            CompletedDateRange(
                modifier = Modifier.padding(64.dp),
                dateRange = LocalDate.of(2020, 1, 8)..LocalDate.of(2020, 1, 31)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DaysIndicatorPreview() {
    SchengenCalculatorTheme {
        DaysIndicator(
            completedDatesList = listOf(
                LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8)
            )
        )
    }
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_2,
)
@Composable
fun CompletedDaysPreviewPhone() {
    CompletedDays(
        completedDatesList = listOf(
            LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8),
            LocalDate.of(2020, 4, 5)..LocalDate.of(2020, 5, 30)
        )
    )
}

@Preview(
    showBackground = true,
    widthDp = 600,
    heightDp = 400,
)
@Composable
fun CompletedDaysPreviewTablet() {
    CompletedDays(
        completedDatesList = listOf(
            LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8),
            LocalDate.of(2020, 4, 5)..LocalDate.of(2020, 5, 30)
        )
    )
}