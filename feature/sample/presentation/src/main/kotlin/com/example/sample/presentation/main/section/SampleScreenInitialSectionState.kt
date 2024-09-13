package com.example.sample.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class SampleScreenInitialSectionState(
    val isFailed: Boolean,
    val errorMessage: String,
    val actionReload: () -> Unit
) : SampleScreenSection

@Composable
internal fun rememberSampleScreenInitialSectionState(
    isFailed: Boolean,
    errorMessage: String,
    actionReload: () -> Unit
): SampleScreenInitialSectionState {
    return remember(errorMessage, actionReload, isFailed) {
        SampleScreenInitialSectionState(isFailed, errorMessage, actionReload)
    }
}