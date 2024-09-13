package com.example.sample.presentation.main.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.sample.domain.model.Post

internal data class SampleScreenLoadedSectionState(
    val listPost: List<Post>,
) : SampleScreenSection

@Composable
internal fun rememberSampleScreenLoadedSectionState(
    listPost: List<Post>,
): SampleScreenLoadedSectionState {
    return remember(listPost) {
        SampleScreenLoadedSectionState(listPost = listPost)
    }
}