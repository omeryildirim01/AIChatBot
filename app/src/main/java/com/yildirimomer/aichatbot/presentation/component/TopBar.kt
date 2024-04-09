package com.yildirimomer.aichatbot.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yildirimomer.aichatbot.R

@Composable
fun TopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .height(36.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = stringResource(id = R.string.app_name),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}