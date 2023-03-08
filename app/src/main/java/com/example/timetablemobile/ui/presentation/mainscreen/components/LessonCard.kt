package com.example.timetablemobile.ui.presentation.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.ui.theme.Gray
import com.example.timetablemobile.ui.theme.IndividualLessonColor
import com.example.timetablemobile.ui.theme.MainGreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LessonCard(
    navController: NavController
) {
    val name = "Название предмета"
    val type = "Тип предмета"
    val time = "8:45 - 10:20"
    val teacher = "преподаватель"
    val classroom = "аудитория"
    val groups = "группы"
    Box(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp, 8.dp)
    ) {
        Card(
            onClick = {
                navController.navigate(
                    Screen.LessonDetailScreen.passLessonInfo(
                        name, type, time, teacher, classroom, groups
                    )
                ) {
                    popUpTo(Screen.MainScreen.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 8.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {
                Box(
                    Modifier
                        .width(12.dp)
                        .fillMaxHeight()
                        .background(IndividualLessonColor)
                        .clip(RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
                )

                /* TODO: сюда данные из ViewModel подгружать */
                Column(
                    Modifier
                        .padding(8.dp, 16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    IconListElement(textValue = teacher, icon = R.drawable.badge)
                    IconListElement(textValue = classroom, icon = R.drawable.meeting_room)
                    IconListElement(textValue = groups, icon = R.drawable.group)
                }
            }
        }

        Card(
            Modifier
                .wrapContentSize()
                .padding(bottom = 8.dp)
                .align(Alignment.BottomEnd)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp),
                    clip = false
                ),
            backgroundColor = MainGreen,
            shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp),
            elevation = 4.dp
        ) {
            Text(
                text = time,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun IconListElement(textValue: String, icon: Int) {
    Row(
        //Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            Modifier.requiredSize(24.dp),
            tint = Gray
        )

        Text(
            text = textValue,
            modifier = Modifier
                .padding(start = 4.dp),
            style = MaterialTheme.typography.caption,
            color = Gray,
            textAlign = TextAlign.Start
        )
    }
}