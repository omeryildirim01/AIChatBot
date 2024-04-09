package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yildirimomer.aichatbot.presentation.HomeViewModel

@Composable
fun ChatListView(modifier: Modifier, data: HomeViewModel.HomeUIState) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier =modifier,
        reverseLayout = true,
        state = listState
    ) {
        itemsIndexed(data.messages.reversed()) { _, message ->
            MessageItemTextView(message)
        }
    }
}