package com.example.home.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.home.presentation.main.section.HomeScreenInitialSection
import com.example.home.presentation.main.section.HomeScreenInitialSectionState
import com.example.home.presentation.main.section.HomeScreenLoadedSection
import com.example.home.presentation.main.section.HomeScreenLoadedSectionState
import com.example.home.presentation.main.section.HomeScreenLoadingSection
import com.example.home.presentation.main.section.HomeScreenLoadingSectionState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToHome: () -> Unit,
    navController: NavController,
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.initLoadData()
    }

    val state = rememberHomeScreenState(
        viewModel = viewModel,
        navigateToSample = navigateToHome,
        navController = navController
    )
    HomeScreenContent(state)
}

@Composable
private fun HomeScreenContent(state: HomeScreenState, modifier: Modifier = Modifier) {
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
        when (val section = state.section) {
            is HomeScreenInitialSectionState -> HomeScreenInitialSection(
                state = section,
                outerPadding = padding
            )

            is HomeScreenLoadedSectionState -> HomeScreenLoadedSection(
                state = section,
                outerPadding = padding
            )

            is HomeScreenLoadingSectionState -> HomeScreenLoadingSection(
                state = section,
                outerPadding = padding
            )
        }
    }
}

@Preview
@Composable
internal fun HomeScreenContentPreview() {
    val state = HomeScreenState(
        section = HomeScreenLoadedSectionState(
            listMusic = emptyList(),
            onPlayAt = {},
        ),
        navigateToSample = {}
    )
    HomeScreenContent(state)

}