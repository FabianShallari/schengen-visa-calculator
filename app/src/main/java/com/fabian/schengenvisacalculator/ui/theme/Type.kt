package com.fabian.schengenvisacalculator.ui.theme

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.R


/*
 * Poppins font family at Google Fonts:
 *
 * https://fonts.google.com/specimen/Poppins
 */
val fonts = fontFamily(
    font(R.font.poppins_light, FontWeight.Light),
    font(R.font.poppins_lightitalic, FontWeight.Light, FontStyle.Italic),
    font(R.font.poppins_regular, FontWeight.Normal),
    font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    font(R.font.poppins_medium, FontWeight.Medium),
    font(R.font.poppins_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    font(R.font.poppins_semibold, FontWeight.SemiBold),
    font(R.font.poppins_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    font(R.font.poppins_bold, FontWeight.Bold),
    font(R.font.poppins_bolditalic, FontWeight.Bold, FontStyle.Italic),
)

val typography = typographyFromDefaults(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold
    ),
    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 40.sp
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        lineHeight = 28.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    caption = TextStyle(
        fontFamily = fonts
    ),
    overline = TextStyle(
        letterSpacing = 0.08.em
    )
)

fun typographyFromDefaults(
    h1: TextStyle?,
    h2: TextStyle?,
    h3: TextStyle?,
    h4: TextStyle?,
    h5: TextStyle?,
    h6: TextStyle?,
    subtitle1: TextStyle?,
    subtitle2: TextStyle?,
    body1: TextStyle?,
    body2: TextStyle?,
    button: TextStyle?,
    caption: TextStyle?,
    overline: TextStyle?
): Typography {
    val defaults = Typography()
    return Typography(
        h1 = defaults.h1.merge(h1),
        h2 = defaults.h2.merge(h2),
        h3 = defaults.h3.merge(h3),
        h4 = defaults.h4.merge(h4),
        h5 = defaults.h5.merge(h5),
        h6 = defaults.h6.merge(h6),
        subtitle1 = defaults.subtitle1.merge(subtitle1),
        subtitle2 = defaults.subtitle2.merge(subtitle2),
        body1 = defaults.body1.merge(body1),
        body2 = defaults.body2.merge(body2),
        button = defaults.button.merge(button),
        caption = defaults.caption.merge(caption),
        overline = defaults.overline.merge(overline)
    )
}


@Composable
private fun Typograhy(text: String) {
    Surface {
        ScrollableColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = text, style = MaterialTheme.typography.overline, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.caption, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.button, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.body2, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h5, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h4, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h3, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h2, textAlign = TextAlign.Center)
            Text(text = text, style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TypographyPreview() {
    SchengenCalculatorTheme(darkTheme = false) {
        Typograhy(text = "Schengen Visa Calculator")
    }
}
