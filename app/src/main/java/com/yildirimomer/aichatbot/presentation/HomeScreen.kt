package com.yildirimomer.aichatbot.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.yildirimomer.aichatbot.presentation.component.ChatScreen
import com.yildirimomer.aichatbot.presentation.component.TopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val data = viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar()
            }
        ) {
            ChatScreen(paddingValues = it, data.value) { message ->
                viewModel.sendMessage(message)
            }
        }
    }
}

