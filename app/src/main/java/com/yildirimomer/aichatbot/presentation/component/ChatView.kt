package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.data.model.ChatMessage
import com.yildirimomer.aichatbot.presentation.HomeViewModel

@Composable
fun ChatScreen(
    paddingValues: PaddingValues,
    state: HomeViewModel.HomeUIState,
    onClick: (ChatMessage) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    ) {

        ChatListView(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            state = state
        )
        ChatInputRow {
            onClick.invoke(it)
        }
    }
}
