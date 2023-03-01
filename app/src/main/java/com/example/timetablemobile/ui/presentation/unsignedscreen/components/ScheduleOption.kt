package com.example.timetablemobile.ui.presentation.unsignedscreen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.ui.theme.MainGreen

@Composable
fun ScheduleOption(name: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MainGreen,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(8.dp, 12.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}