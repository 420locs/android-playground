package com.example.home.presentation.main.section

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
internal fun HomeScreenInitialSection(
    state: HomeScreenInitialSectionState,
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
internal fun HomeScreenInitialSectionStatePreview() {
    val state = HomeScreenInitialSectionState(
        errorMessage = "Something went wrong",
        actionReload = {},
        isFailed = true,
    )
    HomeScreenInitialSection(state, PaddingValues())
}