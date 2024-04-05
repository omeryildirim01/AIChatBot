package com.yildirimomer.aichatbot.data.model

import java.util.UUID

data class PrompterMessage(
    val id: String = UUID.randomUUID().toString(),
    val message: String? = null,
    val type: PrompterType,
    val error: Exception? = null,
    val processing: Boolean = false,
)

enum class PrompterType{
    CLIENT,
    SERVER
}