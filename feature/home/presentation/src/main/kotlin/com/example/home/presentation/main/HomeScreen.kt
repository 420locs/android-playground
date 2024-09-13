package com.example.home.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeScreen(
    navigateToSample: () -> Unit,
) {
    HomeScreenContent(navigateToSample)
}

@Composable
private fun HomeScreenContent(navigateToSample: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "👉 Home Screen 👈",
                    fontSize = 24.sp
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = navigateToSample) {
                Text(text = "Navigate to Sample")
            }
        }
    }
}

@Preview
@Composable
internal fun HomeScreenContentPreview() {
    HomeScreenContent(navigateToSample = {})
}