package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.R
import com.yildirimomer.aichatbot.data.model.ChatMessage
import com.yildirimomer.aichatbot.data.model.MessageType
import com.yildirimomer.aichatbot.ui.theme.AIChatBotTheme

@Composable
fun MessageItemTextView(message: ChatMessage) {
    Column(
        modifier = Modifier.padding(2.dp)
    ) {
        val animState = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = message.id) {
            animState.value = true
        }
        AnimatedVisibility(
            visible = animState.value,
            enter = if (message.type == MessageType.SERVER) expandHorizontally() else expandIn()
        ) {
            MessageLabel(message = message)
        }
    }
}


@Composable
fun MessageLabel(message: ChatMessage) {
    Row(modifier = Modifier.fillMaxWidth()) {

        if (message.processing) {
            RotatingAIIcon(
                painter = painterResource(id = R.drawable.gemini_icon),
                contentDescription = "gemini-ai"
            )
        }
        Spacer(
            modifier = Modifier.weight(if (message.type == MessageType.SERVER) 0.01f else 1f)
        )

        MessageText(modifier = Modifier.weight(5f), message = message)

        Spacer(
            modifier = Modifier.weight(if (message.type == MessageType.CLIENT) 0.01f else 1f)
        )
    }
}

@Composable
fun MessageText(modifier: Modifier, message: ChatMessage) {
    Column(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
            .clip(
                shape =
                if (message.type == MessageType.CLIENT) RoundedCornerShape(
                    12.dp,
                    12.dp,
                    0.dp,
                    12.dp
                )
                else RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp)
            )
            .background(
                color = if (message.type == MessageType.SERVER) Color.DarkGray.copy(alpha = 0.2f) else Color.Gray.copy(
                    alpha = 0.8f
                ),
            ),
    ) {
        Text(
            text = message.message.orEmpty(),
            color = Color.Black,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun  PreviewClientMessage() {
    AIChatBotTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            MessageLabel(
                message = ChatMessage(
                    message = "Hello There",
                    type = MessageType.CLIENT
                )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun  PreviewAIMessage() {
    AIChatBotTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            MessageLabel(
                message = ChatMessage(
                    message = "Hello There",
                    type = MessageType.SERVER
                )
            )
        }
    }
}