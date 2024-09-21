package com.example.sample.presentation.main.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushNotification.ItemNotification
import com.example.pushNotification.SystemTrayNotifier

@Composable
internal fun SampleScreenLoadedSection(
    state: SampleScreenLoadedSectionState,
    outerPadding: PaddingValues,
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(outerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(state.listPost.size) { index ->
            val notifications = remember(state.listPost[index]) {
                ItemNotification(
                    id = state.listPost[index].id ?: index,
                    title = state.listPost[index].title.orEmpty(),
                    body = state.listPost[index].body.orEmpty()
                )
            }
            Text(modifier = Modifier
                .clickable {
                    SystemTrayNotifier.postNotifications(
                        context,
                        listOf(notifications)
                    )
                }
                .padding(vertical = 8.dp, horizontal = 16.dp),
                text = state.listPost[index].title.orEmpty())
        }
    }
}

@Preview
@Composable
internal fun SampleScreenLoadedSectionPreview() {
    val state = SampleScreenLoadedSectionState(
        listPost = emptyList()
    )
    SampleScreenLoadedSection(state, PaddingValues())

}