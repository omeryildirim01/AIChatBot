package com.yildirimomer.aichatbot.data

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.yildirimomer.aichatbot.BuildConfig
import com.yildirimomer.aichatbot.domain.ChatRepository
import java.lang.RuntimeException

class ChatRepositoryImpl : ChatRepository {

    companion object {
        private const val AI_MODEL = "gemini-pro"
    }

    override suspend fun generateContent(request: String): Result<GenerateContentResponse> {
        return try {
            Result.Success(
                GenerativeModel(
                    modelName = AI_MODEL,
                    apiKey = BuildConfig.apiKey
                ).generateContent(request)
            )
        } catch (e: Throwable) {
            Result.Error(RuntimeException(e.localizedMessage))
        }
    }
}