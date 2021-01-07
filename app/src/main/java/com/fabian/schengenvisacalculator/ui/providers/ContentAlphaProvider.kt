package com.fabian.schengenvisacalculator.ui.providers

import androidx.compose.material.AmbientContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers

@Composable
fun ContentAlphaProvider(contentAlpha: Float, content: @Composable () -> Unit) {
    Providers(AmbientContentAlpha provides contentAlpha) {
        content()
    }
}