package com.fabian.schengenvisacalculator.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme

@Composable
fun SchengenCalculatorApp() {
    SchengenCalculatorTheme {
        val navController = rememberNavController()
        Providers(NavControllerAmbient provides navController) {
            NavigationHost()
        }
    }
}


@Composable
fun Greeting(text: String) {
    Text(text = text, textAlign = TextAlign.Center, style = MaterialTheme.typography.h3)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SchengenCalculatorTheme(darkTheme = true) {
        Greeting(text = "Hello World")
    }
}