package com.fabian.schengenvisacalculator.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import com.fabian.schengenvisacalculator.ui.theme.SchengenCalculatorTheme

@Composable
fun SchengenCalculatorApp() {
    SchengenCalculatorTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {
    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting("Hello World")
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text, style = MaterialTheme.typography.overline, textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SchengenCalculatorTheme(darkTheme = true) {
        Greeting(text = "Hello World")
    }
}