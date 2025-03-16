package com.example.home.presentation.playingSong

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.designSystem.design.Button.BackButton
import com.example.designSystem.design.TitledToolbar
import com.example.home.presentation.playingSong.section.PlayingSongScreenInitialSection
import com.example.home.presentation.playingSong.section.PlayingSongScreenInitialSectionState
import com.example.home.presentation.playingSong.section.PlayingSongScreenLoadedSection
import com.example.home.presentation.playingSong.section.PlayingSongScreenLoadedSectionState
import com.example.home.presentation.playingSong.section.PlayingSongScreenLoadingSection
import com.example.home.presentation.playingSong.section.PlayingSongScreenLoadingSectionState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PlayingSongScreen(
    navController: NavController,
    viewModel: PlayingSongViewModel = koinViewModel(),
) {
    val state = rememberPlayingSongScreenState(viewModel, navController)
    PlayingSongContent(
        state = state
    )
}

@Composable
private fun PlayingSongContent(state: PlayingSongScreenState, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        topBar = {
            TitledToolbar(
                title = "", leftButtons = listOf(
                    BackButton(onClick = state.navigateBack)
                )
            )
        }
    ) { padding ->
        when (val section = state.section) {
            is PlayingSongScreenInitialSectionState -> PlayingSongScreenInitialSection(
                state = section,
                outerPadding = padding
            )

            is PlayingSongScreenLoadedSectionState -> PlayingSongScreenLoadedSection(
                state = section,
                outerPadding = padding
            )

            is PlayingSongScreenLoadingSectionState -> PlayingSongScreenLoadingSection(
                state = section,
                outerPadding = padding
            )
        }
    }
}
