package com.yildirimomer.aichatbot.domain

import com.yildirimomer.aichatbot.data.model.ChatMessage
import com.yildirimomer.aichatbot.data.Result
import com.yildirimomer.aichatbot.data.model.MessageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class GenerateChatContentUseCase(private val repository: ChatRepository) {

    suspend fun sendMessage(message: ChatMessage): Flow<Result<ChatMessage>> {
        return flow {
            runCatching {
                when (val result = repository.generateContent(message.message.orEmpty())) {
                    is Result.Error -> {
                        emit(value = Result.Error(result.exception))
                    }

                    is Result.Success -> {
                        emit(
                            value = Result.Success(
                                ChatMessage(
                                    type = MessageType.SERVER,
                                    message = result.data.text
                                )
                            )
                        )
                    }
                }
            }.onFailure {
                emit(value = Result.Error(RuntimeException(it.localizedMessage)))
            }
        }
    }
}