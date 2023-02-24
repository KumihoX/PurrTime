package com.example.timetablemobile.presentation.mainscreen.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.IndividualLessonColor
import com.example.timetablemobile.ui.theme.MainGreen

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
                .wrapContentHeight()
                .padding(end = 8.dp),
            backgroundColor = Color.White
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Box(
                    Modifier
                        .background(IndividualLessonColor)
                        .height(IntrinsicSize.Min)
                        .width(12.dp)
                        .clip(RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
                )

                Column(
                    Modifier
                        .padding(8.dp, 16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Название предмета",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start
                    )

                    Column(
                        Modifier.padding(top = 8.dp)
                    ) {
                        IconListElement(textValue = "преподаватель", icon = R.drawable.badge)
                        IconListElement(textValue = "аудитория", icon = R.drawable.meeting_room)
                        IconListElement(textValue = "группы", icon = R.drawable.group)
                    }
                }
            }
        }

        Box(
            Modifier
                .padding(bottom = 8.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
                .background(MainGreen)
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