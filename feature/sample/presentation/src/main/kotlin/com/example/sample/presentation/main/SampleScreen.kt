package com.example.sample.presentation.main

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.navigation.NavController
import com.example.designSystem.design.MainToolbar
import com.example.sample.domain.model.Post
import com.example.sample.presentation.main.section.SampleScreenInitialSection
import com.example.sample.presentation.main.section.SampleScreenInitialSectionState
import com.example.sample.presentation.main.section.SampleScreenLoadedSection
import com.example.sample.presentation.main.section.SampleScreenLoadedSectionState
import com.example.sample.presentation.main.section.SampleScreenLoadingSection
import com.example.sample.presentation.main.section.SampleScreenLoadingSectionState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SampleScreen(
    navController: NavController,
    viewModel: SampleViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.initLoadData()
    }
    val state = rememberSampleScreenState(viewModel, navController)
    SampleScreenContent(state)
}

@Composable
private fun SampleScreenContent(state: SampleScreenState, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        topBar = {
            MainToolbar(
                title = "🗣️ Sample Screen 🗿",
                notification = true,
                navigateToNotification = {},
                newNotificationsCount = 1
            )
        }
    ) { padding ->
        when (val sectionState = state.section) {
            is SampleScreenInitialSectionState -> SampleScreenInitialSection(
                state = sectionState,
                outerPadding = padding,
            )

            is SampleScreenLoadingSectionState -> SampleScreenLoadingSection(
                state = sectionState,
                outerPadding = padding,
            )

            is SampleScreenLoadedSectionState -> SampleScreenLoadedSection(
                state = sectionState,
                outerPadding = padding,
            )
        }
    }
}

@Preview
@Composable
internal fun SampleScreenLoadedSectionPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    val sampleData = List(25) {
        Post(
            id = it,
            title = "${text.take(17)} $it",
            body = "Body $it",
            userId = it
        )
    }
    val state = SampleScreenState(
        section = SampleScreenLoadedSectionState(
            listPost = sampleData
        )
    )
    SampleScreenContent(state)

}


@Preview
@Composable
internal fun SampleScreenLoadingSectionStatePreview() {
    val state = SampleScreenState(
        section = SampleScreenLoadingSectionState
    )
    SampleScreenContent(state)
}

@Preview
@Composable
internal fun SampleScreenInitialSectionStatePreview() {
    val state = SampleScreenState(
        section = SampleScreenInitialSectionState(
            actionReload = {},
            errorMessage = "Cái này gọi là tạch api",
            isFailed = true,
        )
    )
    SampleScreenContent(state)
}

