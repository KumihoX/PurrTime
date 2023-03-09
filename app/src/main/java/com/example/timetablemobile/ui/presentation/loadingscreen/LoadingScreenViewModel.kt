package com.example.timetablemobile.ui.presentation.loadingscreen

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.data.remote.dto.UserInfoDto
import com.example.timetablemobile.domain.usecase.userInfo.InfoUseCase
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingScreenViewModel @Inject constructor(
    private val infoUseCase: InfoUseCase
) : ViewModel() {

    private val _state: MutableState<LoadingState> = mutableStateOf(LoadingState.Loading)
    var state: State<LoadingState> = _state

    private var scheduleType = ""
    private var typeData = ""

    private var twoRoles = false
    private var caughtException = false

    private fun defineUser(userInfo: UserInfoDto) {
        if (userInfo.teacherId != null && userInfo.group != null) {
            twoRoles = true
        }
        else if (userInfo.teacherId != null) {
            scheduleType = "TEACHER"
            typeData = userInfo.teacherId.id
        }
        else if (userInfo.group != null) {
            scheduleType = "STUDENT"
            typeData = userInfo.group.toString()
        }
    }

    fun checkAuthorization(
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch {
            while (!caughtException) try {
                val userData = infoUseCase(context = context)
                defineUser(userData)
                if (!twoRoles) {
                    navController.navigate(
                        Screen.MainScreen.passScheduleInfo(
                            type = scheduleType,
                            data = typeData
                        )
                    )
                }
                else {
                    navController.navigate(
                        Screen.ChoiceScreen.passScheduleInfo(
                            studentData = userData.group.toString(),
                            teacherData = userData.teacherId.toString()
                        )
                    )
                }
                caughtException = true
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception ) {
                when (ex.message) {
                    "HTTP 401 Unauthorized" -> {
                        caughtException = true
                        navController.navigate(Screen.SignInScreen.route)
                    }
                }
            }
        }
    }
}