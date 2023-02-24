package com.example.timetablemobile.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.ui.theme.Green
import com.example.timetablemobile.ui.theme.IndividualLessonColor

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun LessonCard() {
    Box(
        Modifier
            .padding(4.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(end = 8.dp),
            backgroundColor = Color.White
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Box(
                    Modifier
                        .defaultMinSize(12.dp, 120.dp)
                        .clip(RoundedCornerShape(8.dp, 0.dp,0.dp, 8.dp))
                        .background(IndividualLessonColor)
                )

                Column(
                    Modifier.padding(8.dp, 16.dp)
                ) {
                    Text(text = "Английский язык")
                    Text(text = "Английский язык")
                    Text(text = "Английский язык")
                }
            }
        }

        Box(
            Modifier
                .padding(bottom = 8.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
                .background(Green)
                .align(Alignment.BottomEnd),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "8:45 - 10:20",
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentSize(),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}