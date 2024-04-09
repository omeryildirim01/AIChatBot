package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.data.model.MessageType
import com.yildirimomer.aichatbot.data.model.ChatMessage

@Composable
fun ChatInputRow(
    onSendButtonClick: (message: ChatMessage) -> Unit
) {
    val messageText = remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MessageTextField(
            modifier = Modifier.padding(16.dp).weight(1f),
            onValueChange =  {
                messageText.value = it
            },
            textValue = messageText.value,

        )

        SendButton {
            if (messageText.value.text.isNotEmpty()) {
                onSendButtonClick.invoke(
                    ChatMessage(
                        message = messageText.value.text,
                        type = MessageType.CLIENT,
                    )
                )
                messageText.value = TextFieldValue("")
            }
        }
    }
}