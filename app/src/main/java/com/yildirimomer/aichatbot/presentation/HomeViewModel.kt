package com.yildirimomer.aichatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yildirimomer.aichatbot.data.Result
import com.yildirimomer.aichatbot.data.model.ChatMessage
import com.yildirimomer.aichatbot.data.model.MessageType
import com.yildirimomer.aichatbot.domain.GenerateChatContentUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: GenerateChatContentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState(listOf()))
    val uiState: StateFlow<HomeUIState> = _uiState

    fun sendMessage(message: ChatMessage) {
        viewModelScope.launch {
            updateUIState(message)
            updateUIState(ChatMessage(processing = true, type = MessageType.SERVER))
            useCase.sendMessage(message).collectLatest { result ->
                updateUIState( message =
                    when (result) {
                        is Result.Error -> {
                                ChatMessage(
                                    processing = false,
                                    type = MessageType.SERVER,
                                    message = "An error occurred : ${result.exception.localizedMessage}"
                                )
                        }
                        is Result.Success -> {
                            result.data
                        }
                    }
                )
            }
        }
    }

    private fun updateUIState(
        message: ChatMessage,
        error: String? = null,
        isLoading: Boolean = false
    ) {
        val items = _uiState.value.messages.filter { it.processing.not() }.toMutableList()
        items.add(message)
        _uiState.update {
            it.copy(
                messages = items.toList(),
                isLoading = isLoading,
                error = error,
                token = message.message.orEmpty()
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    data class HomeUIState(
        val messages: List<ChatMessage> = listOf(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val token: String = ""
    )
}