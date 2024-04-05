package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.data.model.PrompterMessage
import com.yildirimomer.aichatbot.data.model.PrompterType
import com.yildirimomer.aichatbot.presentation.HomeViewModel

@Composable
fun ChatScreen(
    paddingValues: PaddingValues,
    data: HomeViewModel.HomeUIState,
    onClick: (PrompterMessage) -> Unit
) {

    val messageText = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            reverseLayout = true
        ) {
            itemsIndexed(data.prompterMessages) { _, message ->
                PrompterMessage(message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText.value,
                onValueChange = { messageText.value = it },
                label = { Text("type here..") },
                modifier = Modifier.padding(16.dp).weight(1f),
                trailingIcon = {
                    Icon(Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                messageText.value = TextFieldValue("")
                            }
                    )
                },
                singleLine = true
            )
            IconButton(onClick = {
                if (messageText.value.text.isNotEmpty()) {
                    onClick.invoke(
                        PrompterMessage(
                            message = messageText.value.text,
                            type = PrompterType.CLIENT,
                        )
                    )
                    messageText.value = TextFieldValue("")
                }
            }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Rounded.Send,
                    contentDescription = "Send",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

    }
}
