package com.fabian.schengenvisacalculator.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val NavControllerAmbient =
    staticAmbientOf<NavHostController> { error("NavController not provided") }

@Composable
fun NavControllerProvider(children: @Composable () -> Unit) {
    val navController = rememberNavController()
    Providers(NavControllerAmbient provides navController) {
        children()
    }
}