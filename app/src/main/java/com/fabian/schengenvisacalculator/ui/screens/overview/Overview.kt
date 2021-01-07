package com.fabian.schengenvisacalculator.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import java.time.LocalDate

@Composable
fun Overview(modifier: Modifier = Modifier, completedDateRanges: List<LocalDateRange>) {
    OverviewLayout(modifier = modifier) {
        DaysIndicator(
            completedDateRanges = completedDateRanges
        )
        DateRangesList(
            completedDateRanges = completedDateRanges
        )
    }
}

@Composable
fun OverviewLayout(modifier: Modifier, content: @Composable () -> Unit) {
    WithConstraints(modifier = modifier) {
        if (constraints.maxWidth >= with(AmbientDensity.current) { 600.dp.toPx() }) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                content()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(top = DAYS_INDICATOR_SIZE_DP / 3)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}

@Composable
private fun OverviewPreview(darkTheme: Boolean) {
    SchengenCalculatorTheme(darkTheme = darkTheme) {
        Surface {
            Overview(
                completedDateRanges = listOf(
                    LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 1),
                    LocalDate.of(2020, 3, 1)..LocalDate.of(2020, 3, 8),
                    LocalDate.of(2020, 4, 5)..LocalDate.of(2020, 5, 30)
                )
            )
        }
    }
}

@Preview(
    "Overview Phone - Dark",
    showBackground = true,
    device = Devices.NEXUS_5,
)
@Composable
private fun OverviewPreviewPhoneDark() {
    OverviewPreview(darkTheme = true)
}

@Preview(
    "Overview Phone - Dark",
    showBackground = true,
    device = Devices.NEXUS_5,
)
@Composable
private fun OverviewPreviewPhoneLight() {
    OverviewPreview(darkTheme = false)
}

@Preview(
    "Overview Tablet - Dark",
    showBackground = true,
    widthDp = 600,
    heightDp = 400,
)
@Composable
private fun OverviewPreviewTabletDark() {
    OverviewPreview(darkTheme = true)
}

@Preview(
    "Overview Tablet - Light",
    showBackground = true,
    widthDp = 600,
    heightDp = 400,
)
@Composable
private fun OverviewPreviewTabletLight() {
    OverviewPreview(darkTheme = false)
}
