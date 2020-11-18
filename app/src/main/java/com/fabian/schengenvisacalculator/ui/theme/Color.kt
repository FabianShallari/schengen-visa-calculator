package com.fabian.schengenvisacalculator.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

/*
* Color swatch taken from Flag of Europe:
* https://commons.wikimedia.org/wiki/File:Flag_of_Europe.svg
*/
val europeBlue = Color(0xFF003399)
val europeBlueLight = Color(0xFF505ccb)
val europeBlueDark = Color(0xFF00106a)
val europeGold = Color(0xFFFFCC00)
/*
val europeGoldLight = Color(0xFFFFFF50)
val europeGoldDark = Color(0xFFC79C00)
val primaryText = Color.White
val secondaryText = Color.Black
*/


/*
 * Color palette can be customized here:
 * https://material.io/resources/color/
 */
internal val DarkColorPalette = darkColors(
    primary = europeBlue,
    primaryVariant = europeBlueLight,
    secondary = europeGold,
    /*
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

internal val LightColorPalette = lightColors(
    primary = europeBlue,
    primaryVariant = europeBlueDark,
    secondary = europeGold,
    /*
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)