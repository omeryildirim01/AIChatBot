package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.yildirimomer.aichatbot.R
import com.yildirimomer.aichatbot.ui.theme.AIChatBotTheme


@Composable
fun MessageTextField(
    modifier: Modifier,
    onValueChange: (TextFieldValue) -> Unit,
    textValue: TextFieldValue = TextFieldValue()
) {

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            onValueChange.invoke(it)
        },
        label = { Text(stringResource(id = R.string.type_here)) },
        modifier = modifier,
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier.clickable {
                    onValueChange.invoke(TextFieldValue(""))
                })
        },
        singleLine = true
    )
}

@PreviewLightDark()
@Composable
private fun PreviewMessageTextField() {
    AIChatBotTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MessageTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), onValueChange = {

                }, textValue = TextFieldValue("")
            )
        }
    }
}