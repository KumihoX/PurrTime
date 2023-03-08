package com.example.timetablemobile.ui.presentation.lessondetailsscreen

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.*
import com.example.timetablemobile.ui.theme.Black
import com.example.timetablemobile.ui.theme.IndividualLessonColor
import com.example.timetablemobile.ui.theme.MainGreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LessonDetailScreen(
    arguments: Bundle,
    navController: NavController
) {
    Box(Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .align(TopCenter)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.LessonDetailScreen.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Start)
                ) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "Возвращает на предыдущую страницу",
                        tint = MainGreen

                    )
                }
            }

            Text(
                text = arguments.getString(LESSON_NAME).toString(),
                style = MaterialTheme.typography.h5,
                color = Black,
                modifier = Modifier
                    .align(Start)
                    .padding(bottom = 4.dp)
            )

            Box(
                Modifier
                    .height(4.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(IndividualLessonColor)
            )

            Text(
                text = arguments.getString(LESSON_TYPE).toString(),
                style = MaterialTheme.typography.body2,
                color = IndividualLessonColor,
                modifier = Modifier
                    .align(Start)
                    .padding(top = 6.dp, bottom = 16.dp)
            )

            Image(
                painter = painterResource(R.drawable.clock),
                contentDescription = "Картинка часов",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 6.dp)
            )

            Text(
                text = arguments.getString(LESSON_TIME).toString(),
                style = MaterialTheme.typography.h6,
                color = Black,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 20.dp)
            )

            EnabledIconListElement(
                textValue = arguments.getString(LESSON_TEACHER).toString(),
                icon = R.drawable.badge
            )
            EnabledIconListElement(
                textValue = arguments.getString(LESSON_CLASSROOM).toString(),
                icon = R.drawable.meeting_room
            )
            DisabledIconListElement(
                textValue = arguments.getString(LESSON_GROUPS).toString(),
                icon = R.drawable.group
            )
        }
        Image(
            painter = painterResource(R.drawable.cat_clock),
            contentDescription = "Котик с часами мордочкой",
            Modifier
                .align(BottomCenter)
        )
    }

}

@Composable
fun EnabledIconListElement(textValue: String, icon: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clickable { }
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                Modifier
                    .requiredSize(30.dp),
                tint = Black
            )

            Text(
                text = textValue,
                modifier = Modifier
                    .padding(start = 8.dp),
                style = MaterialTheme.typography.body1,
                color = Black,
                textAlign = TextAlign.Start
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_forward_ios),
            contentDescription = null,
            Modifier
                .requiredSize(20.dp),
            tint = MainGreen
        )
    }
}

@Composable
fun DisabledIconListElement(textValue: String, icon: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                Modifier
                    .requiredSize(30.dp),
                tint = Black
            )

            Text(
                text = textValue,
                modifier = Modifier
                    .padding(start = 8.dp),
                style = MaterialTheme.typography.body1,
                color = Black,
                textAlign = TextAlign.Start
            )
        }
    }
}