package com.example.timetablemobile.ui.presentation.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.data.remote.dto.toWeeklySchedule
import com.example.timetablemobile.domain.model.Lesson
import com.example.timetablemobile.domain.model.WeeklySchedule
import com.example.timetablemobile.domain.usecase.logout.LogoutUseCase
import com.example.timetablemobile.domain.usecase.schedule.GetCabinetScheduleUseCase
import com.example.timetablemobile.domain.usecase.schedule.GetGroupScheduleUseCase
import com.example.timetablemobile.domain.usecase.schedule.GetTeacherScheduleUseCase
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGroupScheduleUseCase: GetGroupScheduleUseCase,
    private val getCabinetScheduleUseCase: GetCabinetScheduleUseCase,
    private val getTeacherScheduleUseCase: GetTeacherScheduleUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _state: MutableState<MainState> = mutableStateOf(MainState.Loading)
    var state: State<MainState> = _state

    private val _header: MutableState<String> = mutableStateOf("")
    var header: State<String> = _header

    private var type = ""
    private var id = ""
    private var info = ""

    private var weekDeviation = 0

    private val _helpDialogIsOpen = mutableStateOf(false)
    var helpDialogIsOpen: State<Boolean> = _helpDialogIsOpen

    private val _weeklySchedule: MutableState<WeeklySchedule> = mutableStateOf(WeeklySchedule())
    var weeklySchedule: State<WeeklySchedule> = _weeklySchedule

    private val _currLessonList = mutableStateOf<List<Lesson>>(emptyList())
    var currLessonList: State<List<Lesson>> = _currLessonList

    private val _selectedDayOfWeek = mutableStateOf(1)
    var selectedDayOfWeek: State<Int> = _selectedDayOfWeek

    fun onSelectedDayOfWeekChange(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY

        calendar.time = date
        _selectedDayOfWeek.value = calendar[Calendar.DAY_OF_WEEK]

        updateCurrentLessonList()
    }

    init {
        _state.value = MainState.Initial
    }

    private fun updateCurrentLessonList() {
        _currLessonList.value = when (_selectedDayOfWeek.value) {
            1 -> _weeklySchedule.value.sunday
            2 -> _weeklySchedule.value.monday
            3 -> _weeklySchedule.value.tuesday
            4 -> _weeklySchedule.value.wednesday
            5 -> _weeklySchedule.value.thursday
            6 -> _weeklySchedule.value.friday
            7 -> _weeklySchedule.value.saturday
            else -> emptyList()
        }
    }

    private fun teacherHeader(info: String) {
        _header.value = info
    }

    private fun cabinetHeader(info: String) {
        _header.value = info
    }

    private fun studentHeader(info: String) {
        _header.value = "Группа $info"
    }

    private fun getTeacherSchedule(
        startDate: String,
        endDate: String,
    ) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val result = getTeacherScheduleUseCase(id, startDate, endDate)
                _state.value = MainState.Content(result.toWeeklySchedule())

                _weeklySchedule.value = result.toWeeklySchedule()
                updateCurrentLessonList()

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = MainState.Error(ex.message ?: "An unexpected error occurred")
            }
        }
    }

    private fun getCabinetSchedule(
        startDate: String,
        endDate: String,
    ) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val result = getCabinetScheduleUseCase(id.toInt(), startDate, endDate)
                _state.value = MainState.Content(result.toWeeklySchedule())

                _weeklySchedule.value = result.toWeeklySchedule()
                updateCurrentLessonList()

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = MainState.Error(ex.message ?: "An unexpected error occurred")
            }
        }
    }

    private fun getStudentSchedule(
        startDate: String,
        endDate: String,
    ) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val result = getGroupScheduleUseCase(id, startDate, endDate)
                _state.value = MainState.Content(result.toWeeklySchedule())

                _weeklySchedule.value = result.toWeeklySchedule()
                updateCurrentLessonList()

            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = MainState.Error(ex.message ?: "An unexpected error occurred")
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStartAndEndData(): ArrayList<String> {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY

        _selectedDayOfWeek.value = calendar.get(Calendar.DAY_OF_WEEK)

        calendar.add(Calendar.WEEK_OF_MONTH, weekDeviation)
        calendar[Calendar.DAY_OF_WEEK] = calendar.firstDayOfWeek
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val days: ArrayList<String> = arrayListOf()

        days.add(simpleDateFormat.format(calendar.time))
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        days.add(simpleDateFormat.format(calendar.time))

        return days
    }

    fun increaseWeekDeviation() {
        weekDeviation += 1
        getScreenInfo()
    }

    fun decreaseWeekDeviation() {
        weekDeviation -= 1
        getScreenInfo()

    }

    fun getSchedule(
        startDate: String,
        endDate: String
    ) {
        when (type) {
            "TEACHER" -> getTeacherSchedule(startDate, endDate)
            "CABINET" -> getCabinetSchedule(startDate, endDate)
            "STUDENT" -> getStudentSchedule(startDate, endDate)
        }
    }


    fun getScreenInfo(
        data: String = info,
        dataId: String = id,
        scheduleType: String = type
    ) {
        val days = getStartAndEndData()
        val startDate = days[0]
        val endDate = days[1]

        when (scheduleType) {
            "TEACHER" -> {
                teacherHeader(data)
                info = data
            }
            "CABINET" -> {
                cabinetHeader(data)
                info = data
            }
            "STUDENT" -> {
                studentHeader(dataId)
            }
        }
        type = scheduleType
        id = dataId
        getSchedule(startDate, endDate)
    }

    fun logout(
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                logoutUseCase(context = context)
                navController.navigate(Screen.SignInScreen.route) {
                    popUpTo(Screen.MainScreen.route) { inclusive = true }
                }
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                if (ex.message == "HTTP 401 Unauthorized") {
                    navController.navigate(Screen.SignInScreen.route) {
                        popUpTo(Screen.MainScreen.route) { inclusive = true }
                    }
                } else {
                    _state.value = MainState.Error("Что-то пошло не так")
                }
            }
        }
    }

    fun openDialog() {
        _helpDialogIsOpen.value = true
    }

    fun closeDialog() {
        _helpDialogIsOpen.value = false
    }
}