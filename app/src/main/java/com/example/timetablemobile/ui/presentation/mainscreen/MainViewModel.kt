package com.example.timetablemobile.ui.presentation.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.data.remote.dto.LessonDto
import com.example.timetablemobile.data.remote.dto.ScheduleDto
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

    private val _helpDialogIsOpen = mutableStateOf(false)
    var helpDialogIsOpen: State<Boolean> = _helpDialogIsOpen

    private fun teacherHeader(info: String) {
        val teacherName = info.split("=").last()
        _header.value = teacherName.substring(0, teacherName.length - 2)
    }

    private fun cabinetHeader(info: String) {
        _header.value = "Учебная аудитория $info"
    }

    private fun studentHeader(info: String) {
        _header.value = "Группа $info"
    }

    private fun getTeacherId(info: String): String {
        val teacherId = info.split("=")[1]
        return teacherId.substring(0, teacherId.length - 6)
    }

    private fun getTeacherSchedule(
        startDate: String,
        endDate: String,
    ) {
        viewModelScope.launch {
            try {
                val result = getTeacherScheduleUseCase(id, startDate, endDate)
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
            try {
                val result = getCabinetScheduleUseCase(id.toInt(), startDate, endDate)
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
            try {
                val result = getGroupScheduleUseCase(id, startDate, endDate)
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
        calendar.add(Calendar.WEEK_OF_MONTH, 0)
        calendar[Calendar.DAY_OF_WEEK] = calendar.firstDayOfWeek
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val days: ArrayList<String> = arrayListOf()

        days.add(simpleDateFormat.format(calendar.time))
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        days.add(simpleDateFormat.format(calendar.time))

        return days
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
        typeData: String,
        scheduleType: String
    ) {
        val days = getStartAndEndData()
        val startDate = days[0]
        val endDate = days[1]

        when (scheduleType) {
            "TEACHER" -> {
                teacherHeader(typeData)
                val TeacherId = getTeacherId(typeData)
                type = scheduleType
                id = TeacherId
            }
            "CABINET" -> {
                cabinetHeader(typeData)
                type = scheduleType
                id = typeData
            }
            "STUDENT" -> {
                studentHeader(typeData)
                type = scheduleType
                id = typeData
            }
        }
        getSchedule(startDate, endDate)
        _state.value = MainState.Initial
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