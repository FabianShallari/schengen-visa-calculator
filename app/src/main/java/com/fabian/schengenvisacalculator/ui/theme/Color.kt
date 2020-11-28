package com.fabian.schengenvisacalculator.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.fabian.schengenvisacalculator.R

/*
 * Color palette can be customized here:
 * https://material.io/resources/color/
 *
 * Using XML colors for now for 2 reasons:
 *
 * 1. Don't repeat colors declarations across xml and compose
 * 2. Android Studio doesn't support the color preview for colors declared using Compose
 */
@Composable
val DarkColorPalette
    get() = darkColors(
        primary = colorResource(id = R.color.europe_blue),
        onPrimary = colorResource(id = R.color.white),
        primaryVariant = colorResource(id = R.color.europe_blue_dark),
        secondary = colorResource(id = R.color.europe_gold),
        onSecondary = colorResource(id = R.color.black),
        background = colorResource(id = R.color.black),
        onBackground = colorResource(id = R.color.white),
        surface = colorResource(id = R.color.black),
        onSurface = colorResource(id = R.color.white)
    )

@Composable
val LightColorPalette
    get() = lightColors(
        primary = colorResource(id = R.color.europe_blue),
        onPrimary = colorResource(id = R.color.white),
        primaryVariant = colorResource(id = R.color.europe_blue_light),
        secondary = colorResource(id = R.color.europe_gold),
        onSecondary = colorResource(id = R.color.black),
        secondaryVariant = colorResource(id = R.color.europe_gold_light),
        background = colorResource(id = R.color.white),
        onBackground = colorResource(id = R.color.black),
        surface = colorResource(id = R.color.white),
        onSurface = colorResource(id = R.color.black)
    )