package com.example.sample.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object SampleScreenLoadingSectionState : SampleScreenSection

@Composable
internal fun rememberSampleScreenLoadingSectionState(): SampleScreenLoadingSectionState {
    return remember {
        SampleScreenLoadingSectionState
    }
}