package com.example.timetablemobile.ui.presentation.mainscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.ui.presentation.mainscreen.components.ColorAlertDialog
import com.example.timetablemobile.ui.presentation.mainscreen.components.LessonCard
import com.example.timetablemobile.ui.theme.*
import java.util.*

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    var day by remember { mutableStateOf(Date()) }
    val helpDialogIsOpen: Boolean by remember { viewModel.helpDialogIsOpen }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    )
    {
        Column(modifier = Modifier.fillMaxWidth())
        {
            TopBar(onSelectedDayChange = { day = it }, viewModel = viewModel, navController)
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                LessonCard(navController)
                LessonCard(navController)
                LessonCard(navController)
                LessonCard(navController)
                LessonCard(navController)
                LessonCard(navController)
            }
        }
        if (helpDialogIsOpen) {
            ColorAlertDialog(viewModel = viewModel)
        }
    }
}

@Composable
fun TopBar(
    onSelectedDayChange: (Date) -> Unit,
    viewModel: MainViewModel,
    navController: NavController
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
                Exit(navController)
                Info(currentDate = currentDate)
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
                },
                onPrevWeekClicked = {
                    calendar.time = it
                    currentDate = it
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Exit(navController: NavController) {
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        IconButton(onClick = { navController.navigate(Screen.SignInScreen.route){
            popUpTo(Screen.MainScreen.route) { inclusive = true }
        } }) {
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
    currentDate: Date
) {
    val monthName = getMonthName(currentDate).replaceFirstChar { it.uppercase() }
    val yearName = getYear4Letters(currentDate)
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Группа 972103",
            style = MaterialTheme.typography.h5,
            color = Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = "$monthName $yearName",
            style = MaterialTheme.typography.subtitle1,
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
                Text(text = getDayNumber(day),
                    style = MaterialTheme.typography.body1,
                    color = if (selectedDate == day) White else Black,

                    modifier = (if (selectedDate == day) Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp)
                        .drawBehind {
                            drawRoundRect(
                                color = MainGreen,
                                size = Size(width = 30.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 5.dp.toPx(), 5.dp.toPx()),
                                topLeft = Offset(x = 6.dp.toPx(), y = -(6).dp.toPx())
                            )
                        }
                    else Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp)).clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onSelectDay(day)
                    },
                    textAlign = TextAlign.Center
                )
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



