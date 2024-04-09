package com.yildirimomer.aichatbot.domain

import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.yildirimomer.aichatbot.data.Result

interface ChatRepository {
    suspend fun generateContent(request: String): Result<GenerateContentResponse>
}