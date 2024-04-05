package com.yildirimomer.aichatbot.domain

interface MainRepository {
    suspend fun send(promptMessage: String): String
}