package com.yildirimomer.aichatbot.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.R
import com.yildirimomer.aichatbot.data.model.PrompterMessage
import com.yildirimomer.aichatbot.data.model.PrompterType
import com.yildirimomer.aichatbot.ui.theme.AIChatBotTheme

@Composable
fun PrompterMessage(message: PrompterMessage) {
    Column(
        modifier = Modifier.padding(2.dp)
    ) {
        val animState = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = message) {
            animState.value = true
        }
        AnimatedVisibility(
            visible = animState.value,
            enter = if (message.type == PrompterType.SERVER) expandHorizontally()   else expandIn()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),

            ) {

                if (message.processing) {
                    RotatingAIIcon()
                }
                Text(
                    text = "",
                    modifier = Modifier.weight(if (message.type == PrompterType.SERVER) 0.01f else 1f)
                )
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                        .clip(shape =
                        if (message.type == PrompterType.CLIENT) RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp)
                        else RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp))
                        .background(
                            color = if (message.type == PrompterType.SERVER) Color.DarkGray.copy(alpha = 0.2f) else Color.Gray.copy(
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
                Text(
                    text = "",
                    modifier = Modifier.weight(if (message.type == PrompterType.CLIENT) 0.01f else 1f)
                )
            }
        }
    }
}

@Composable
fun RotatingAIIcon() {
    val infiniteTransition = rememberInfiniteTransition(label = "transitive")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotate"
    )
    Column {
        Image(
            painter = painterResource(id = R.drawable.gemini_icon),
            contentDescription = "gemini-icon",
            modifier = Modifier
                .size(40.dp)
                .rotate(angle)
        )
    }
}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun PrompterPreview() {
    AIChatBotTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PrompterMessage(
                message = PrompterMessage(
                    message = "Hello There",
                    type = PrompterType.CLIENT
                )
            )
        }
    }
}