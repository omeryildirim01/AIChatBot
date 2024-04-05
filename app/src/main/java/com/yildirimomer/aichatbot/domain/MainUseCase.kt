package com.yildirimomer.aichatbot.domain

import com.yildirimomer.aichatbot.data.model.PrompterMessage
import com.yildirimomer.aichatbot.data.model.PrompterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainUseCase(private val repository: MainRepository) {

    suspend fun sendMessage(message: PrompterMessage): Flow<Result<PrompterMessage>> {
        return flow {
            runCatching {
                repository.send(message.message.orEmpty())
            }.onFailure {
                emit(Result.failure(it))
            }.onSuccess {
                emit(Result.success(PrompterMessage(message = it, type = PrompterType.SERVER)))
            }
        }
    }
}