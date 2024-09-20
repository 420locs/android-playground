package com.example.home.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeScreenLoadedSection(
    state: HomeScreenLoadedSectionState,
    outerPadding: PaddingValues,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(outerPadding)
    ) {
        Text(text = "Loaded")
    }
}

@Preview
@Composable
internal fun HomeScreenLoadedSectionPreview() {
    val state = HomeScreenLoadedSectionState(
        listPost = emptyList()
    )
    HomeScreenLoadedSection(state, PaddingValues())

}