package com.fabian.schengenvisacalculator.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val AmbientNavigator =
    staticAmbientOf<NavHostController> { error("Navigator not provided") }

@Composable
fun NavigatorProvider(content: @Composable () -> Unit) {
    val navHostController = rememberNavController()

    Providers(AmbientNavigator provides navHostController) {
        content()
    }
}