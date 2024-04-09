package com.yildirimomer.aichatbot.data.model

import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val message: String? = null,
    val type: MessageType,
    val error: Exception? = null,
    val processing: Boolean = false,
)

enum class MessageType{
    CLIENT,
    SERVER
}