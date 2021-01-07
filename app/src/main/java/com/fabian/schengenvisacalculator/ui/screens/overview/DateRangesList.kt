package com.fabian.schengenvisacalculator.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.East
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.schengenvisacalculator.R
import com.fabian.schengenvisacalculator.ui.providers.AmbientDateTimeFormatter
import com.fabian.schengenvisacalculator.ui.providers.ContentAlphaProvider
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme
import com.fabian.schengenvisacalculator.util.LocalDateRange
import com.fabian.schengenvisacalculator.util.periodInDays
import com.fabian.schengenvisacalculator.util.quantityStringResource
import com.fabian.schengenvisacalculator.util.rangeOf
import java.time.LocalDate
import java.util.*

@Composable
fun DateRangesList(
    modifier: Modifier = Modifier,
    completedDateRanges: List<LocalDateRange>,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            all = 16.dp
        ),
        modifier = modifier
            .fillMaxHeight()
            .wrapContentWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            ContentAlphaProvider(contentAlpha = ContentAlpha.medium
            ) {
                Text(
                    text = stringResource(id = R.string.completed).toUpperCase(Locale.ROOT),
                    style = MaterialTheme.typography.caption
                )
            }
        }
        items(completedDateRanges) { item ->
            CompletedDateRange(dateRange = item)
        }
    }
}


@Composable
private fun CompletedDateRange(modifier: Modifier = Modifier, dateRange: LocalDateRange) {
    val completedDays = dateRange.periodInDays(includeLastDate = true).toInt()

    Card(
        modifier = modifier
            .padding(vertical = 6.dp)
            .wrapContentSize(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = dateRange.start.format(AmbientDateTimeFormatter.current),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
                Icon(imageVector = Icons.Sharp.East)
                Text(
                    text = dateRange.endInclusive.format(AmbientDateTimeFormatter.current),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                ContentAlphaProvider(contentAlpha = ContentAlpha.medium) {
                    Text(
                        text = quantityStringResource(
                            id = R.plurals.days,
                            quantity = completedDays,
                            completedDays
                        ),
                        style = MaterialTheme.typography.body2.copy(
                            color = contentColorFor(color = MaterialTheme.typography.body2.color)
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun DateRangesListPreview(darkTheme: Boolean) {
    SchengenCalculatorTheme(darkTheme = darkTheme) {
        Surface {
            DateRangesList(
                completedDateRanges = listOf(
                    rangeOf(
                        startInclusive = LocalDate.of(2020, 1, 8),
                        endInclusive = LocalDate.of(2020, 1, 31)
                    )
                )
            )
        }
    }
}

@Preview("Date Ranges List - Dark", showBackground = true)
@Composable
private fun DateRangesListPreviewDark() {
    DateRangesListPreview(darkTheme = true)
}

@Preview("Date Ranges List - Light", showBackground = true)
@Composable
private fun DateRangesListPreviewLight() {
    DateRangesListPreview(darkTheme = false)
}
