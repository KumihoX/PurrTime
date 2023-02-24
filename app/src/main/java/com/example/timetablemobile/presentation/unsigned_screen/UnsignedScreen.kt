package com.example.timetablemobile.presentation.unsigned_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.presentation.unsigned_screen.components.ScheduleOption

@Preview(showBackground = true)
@Composable
fun UnsignedScreen() {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.cat_heart_above),
            contentDescription = null,
            modifier = Modifier
                .width(240.dp),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = stringResource(R.string.which_schedule_want_to_see),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 28.dp, 16.dp, 32.dp),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
        ) {
            ScheduleOption(name = stringResource(R.string.groups))
            ScheduleOption(name = stringResource(R.string.teachers))
            ScheduleOption(name = stringResource(R.string.classrooms))
        }
    }
}