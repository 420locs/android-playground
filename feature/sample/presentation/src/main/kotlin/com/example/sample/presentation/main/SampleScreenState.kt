package com.example.sample.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.sample.presentation.main.section.SampleScreenSection
import com.example.sample.presentation.main.section.rememberSampleScreenInitialSectionState
import com.example.sample.presentation.main.section.rememberSampleScreenLoadingSectionState
import com.example.sample.presentation.main.section.rememberSampleScreenLoadedSectionState

internal data class SampleScreenState(
    val section: SampleScreenSection,
)

@Composable
internal fun rememberSampleScreenState(viewModel: SampleViewModel): SampleScreenState {
    val isFirstLoadLoading = viewModel.isFirstLoadLoading
    val listPost = viewModel.listData

    val listPostResult = listPost?.getOrNull()
    val section = when {
        isFirstLoadLoading -> rememberSampleScreenLoadingSectionState()
        listPostResult != null && listPost.isSuccess -> rememberSampleScreenLoadedSectionState(
            listPost = listPostResult,
        )

        else -> rememberSampleScreenInitialSectionState(
            errorMessage = "Call api chết mẹ r check lại đi",
            actionReload = viewModel::refreshData,
            isFailed = listPost?.isFailure ?: false
        )
    }

    return remember(section) {
        SampleScreenState(section = section)
    }

}