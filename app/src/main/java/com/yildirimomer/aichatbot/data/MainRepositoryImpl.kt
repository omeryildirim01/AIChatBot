package com.yildirimomer.aichatbot.data

import com.google.ai.client.generativeai.GenerativeModel
import com.yildirimomer.aichatbot.data.model.Helper
import com.yildirimomer.aichatbot.domain.MainRepository

class MainRepositoryImpl: MainRepository {

    override suspend fun send(promptMessage: String)
    = GenerativeModel(modelName = "gemini-pro", apiKey = Helper.getKey()).generateContent(promptMessage).text.toString()
}