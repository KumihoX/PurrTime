package com.example.timetablemobile.ui.presentation.mainscreen

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.domain.model.Lesson
import com.example.timetablemobile.navigation.DATA
import com.example.timetablemobile.navigation.DATA_ID
import com.example.timetablemobile.navigation.SCHEDULE_TYPE
import com.example.timetablemobile.ui.presentation.common.ErrorAlertDialog
import com.example.timetablemobile.ui.presentation.mainscreen.components.ColorAlertDialog
import com.example.timetablemobile.ui.presentation.mainscreen.components.LessonCard
import com.example.timetablemobile.ui.theme.*
import java.util.*

@Composable
fun MainScreen(
    scheduleType: Bundle,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by remember { viewModel.state }
    val helpDialogIsOpen: Boolean by remember { viewModel.helpDialogIsOpen }

    val header by remember { viewModel.header }
    var day by remember { mutableStateOf(Date()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    )
    {
        if (state == MainState.Initial) {
            viewModel.getScreenInfo(
                data = scheduleType.getString(DATA).toString(),
                dataId = scheduleType.getString(DATA_ID).toString(),
                scheduleType = scheduleType.getString(SCHEDULE_TYPE).toString()
            )
        }
        Column(modifier = Modifier.fillMaxWidth())
        {
            TopBar(
                onSelectedDayChange = {
                    day = it
                    viewModel.onSelectedDayOfWeekChange(it)
                },
                viewModel = viewModel,
                navController = navController,
                header = header
            )
            when (state) {
                is MainState.Content -> {
                    val currLessonList: List<Lesson> by remember { viewModel.currLessonList }
                    if (currLessonList.isEmpty()) {
                        EmptyMainScreen()
                    } else {
                        LazyColumn {
                            items(currLessonList) { lesson ->
                                LessonCard(
                                    navController = navController,
                                    name = lesson.subject,
                                    type = lesson.type,
                                    time = lesson.time,
                                    teacher = lesson.teacher.name,
                                    teacherId = lesson.teacher.id,
                                    classroomName = lesson.cabinet.name,
                                    classroomId = lesson.cabinet.id.toString(),
                                    groups = lesson.groups
                                )
                            }
                        }
                    }

                    if (helpDialogIsOpen) {
                        ColorAlertDialog(viewModel = viewModel)
                    }
                }

                MainState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize())
                    {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Center),
                            color = MainGreen
                        )
                    }
                }

                is MainState.Error -> {
                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        TopBar(
                            onSelectedDayChange = { day = it },
                            viewModel = viewModel,
                            navController = navController,
                            header = header
                        )
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        }
                    }
                    if (helpDialogIsOpen) {
                        ColorAlertDialog(viewModel = viewModel)
                    }
                    ErrorAlertDialog(message = (state as MainState.Error).error)
                }
            }
        }
    }
}

@Composable
fun EmptyMainScreen() {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cat_sleepy),
                contentDescription = null,
                Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = stringResource(R.string.no_lessons_this_day),
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                color = Black
            )

            Text(
                text = stringResource(R.string.maybe_lessons_will_be_added_later),
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                color = Gray
            )
        }
    }
}

@Composable
fun TopBar(
    onSelectedDayChange: (Date) -> Unit,
    viewModel: MainViewModel,
    navController: NavController,
    header: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
    )
    {
        val calendar = Calendar.getInstance(Locale.getDefault())
        var selectedDate by rememberSaveable { mutableStateOf(calendar.time) }
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        var currentDate by rememberSaveable { mutableStateOf(calendar.time) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Exit(viewModel, navController)
                Info(currentDate = currentDate, header = header)
                Help(viewModel = viewModel)
            }
            WeekScrollableElement(
                selectedDate = selectedDate,
                currentDate = currentDate,
                onSelectDay = { day ->
                    calendar.time = day
                    selectedDate = day
                    onSelectedDayChange(day)
                },
                onNextWeekClicked = {
                    calendar.time = it
                    currentDate = it
                    selectedDate = it
                    viewModel.increaseWeekDeviation()
                    onSelectedDayChange(it)
                },
                onPrevWeekClicked = {
                    calendar.time = it
                    currentDate = it
                    selectedDate = it
                    viewModel.decreaseWeekDeviation()
                    onSelectedDayChange(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Exit(
    viewModel: MainViewModel,
    navController: NavController
) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        val context = LocalContext.current
        IconButton(onClick = { viewModel.logout(navController, context) }) {
            Icon(
                painter = painterResource(R.drawable.logout),
                contentDescription = "Выход из аккаунта",
                tint = Black
            )
        }
    }
}

@Composable
fun Info(
    currentDate: Date,
    header: String
) {
    val monthName = getMonthName(currentDate).replaceFirstChar { it.uppercase() }
    val yearName = getYear4Letters(currentDate)
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.h6,
            color = Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = "$monthName $yearName",
            style = MaterialTheme.typography.body2,
            color = Gray,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Help(viewModel: MainViewModel) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        IconButton(onClick = { viewModel.openDialog() }) {
            Icon(
                painter = painterResource(R.drawable.help),
                contentDescription = "Открытие окна с подсказкой",
                tint = Black
            )
        }
    }
}

@Composable
fun WeekScrollableElement(
    selectedDate: Date,
    currentDate: Date,
    onSelectDay: (Date) -> Unit,
    onNextWeekClicked: (firstDayDate: Date) -> Unit,
    onPrevWeekClicked: (firstDayDate: Date) -> Unit
) {
    WeekDays(
        firstDayDate = currentDate,
        selectedDate = selectedDate,
        onSelectDay = onSelectDay,
        onNextWeekClicked = onNextWeekClicked,
        onPrevWeekClicked = onPrevWeekClicked
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeekDays(
    modifier: Modifier = Modifier,
    firstDayDate: Date,
    selectedDate: Date,
    onSelectDay: (Date) -> Unit,
    onNextWeekClicked: (firstDayDate: Date) -> Unit,
    onPrevWeekClicked: (firstDayDate: Date) -> Unit
) {

    val weekFinalDays = getFutureDates(6, Calendar.getInstance().apply { time = firstDayDate })
    val interactionSource = remember { MutableInteractionSource() }
    val weekFinalDate = weekFinalDays.last()

    Row(
        modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "Переход на предыдущую неделю",
                tint = MainGreen,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(end = 8.dp)
                    .clickable {
                        val c = Calendar.getInstance()
                        c.time = firstDayDate
                        c.add(Calendar.DATE, -7)
                        val prevWeekFirstDay = c.time
                        onPrevWeekClicked(prevWeekFirstDay)
                    }
            )
        }

        for (day in weekFinalDays) {
            Column(
                modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = getDay3LettersName(day).replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.body2,
                    color = Gray,
                    textAlign = TextAlign.Center
                )

                val color = if (selectedDate == day) MainGreen else Color.Transparent
                Box(
                    Modifier
                        .padding(top = 9.dp)
                        .wrapContentSize()
                        .clip(RoundedCornerShape(5.dp))
                        .background(color)
                ) {
                    Text(
                        text = getDayNumber(day),
                        style = MaterialTheme.typography.body1,
                        color = if (selectedDate == day) White else Black,

                        modifier = Modifier
                            .padding(6.dp, 8.dp)
                            .wrapContentHeight()
                            .width(18.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                        ) {
                            onSelectDay(day)
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Icon(
                painter = painterResource(R.drawable.arrow_forward),
                contentDescription = "Переход на следующую неделю",
                tint = MainGreen,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(start = 8.dp)
                    .clickable {
                        val c = Calendar.getInstance()
                        c.time = weekFinalDate
                        c.add(Calendar.DATE, 1)
                        val nextWeekFirstDay = c.time
                        onNextWeekClicked(nextWeekFirstDay)
                    }
            )
        }
    }
}