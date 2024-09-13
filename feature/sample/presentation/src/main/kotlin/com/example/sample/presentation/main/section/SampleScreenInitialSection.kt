package com.example.sample.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SampleScreenInitialSection(
    state: SampleScreenInitialSectionState,
    outerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(outerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.isFailed) {
            Text(text = state.errorMessage)
            Button(onClick = state.actionReload) {
                Text(text = "Reload")
            }
        }
    }
}


@Preview
@Composable
internal fun SampleScreenInitialSectionStatePreview() {
    val state = SampleScreenInitialSectionState(
        errorMessage = "Something went wrong",
        actionReload = {},
        isFailed = true,
    )
    SampleScreenInitialSection(state, PaddingValues())
}