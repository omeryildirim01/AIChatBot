package com.yildirimomer.aichatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yildirimomer.aichatbot.data.model.PrompterMessage
import com.yildirimomer.aichatbot.data.model.PrompterType
import com.yildirimomer.aichatbot.domain.MainUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: MainUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState>(HomeUIState(listOf()))
    val uiState: StateFlow<HomeUIState> = _uiState

    fun prompt(message: PrompterMessage): Unit {
        viewModelScope.launch {
            updateUIState(message)
            updateUIState(PrompterMessage(processing = true, type = PrompterType.SERVER))
            useCase.sendMessage(message).collectLatest { result ->
                when {
                    result.isSuccess -> {
                        result.getOrNull()?.let { data ->
                            updateUIState(message = data)
                        }
                    }
                    result.isFailure -> {
                        result.exceptionOrNull()?.let { ex ->
                            updateUIState(
                                PrompterMessage(
                                    message = null,
                                    type = PrompterType.SERVER,
                                    error = IllegalArgumentException(ex.localizedMessage)
                            )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateUIState(message: PrompterMessage, error: String? = null, isLoading: Boolean = false) {
        val items =  _uiState.value.prompterMessages.filter { it.processing.not() }.toMutableList()
        items.add(message)
        _uiState.update {
            it.copy(
                prompterMessages = items.toList(),
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
        val prompterMessages: List<PrompterMessage> = listOf(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val token: String = ""
    )
}