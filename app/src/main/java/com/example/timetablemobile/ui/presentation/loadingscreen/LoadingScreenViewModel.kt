package com.example.timetablemobile.ui.presentation.loadingscreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.data.remote.dto.UserInfoDto
import com.example.timetablemobile.domain.usecase.token.GetTokenUseCase
import com.example.timetablemobile.domain.usecase.token.RefreshTokenUseCase
import com.example.timetablemobile.domain.usecase.token.SaveTokenUseCase
import com.example.timetablemobile.domain.usecase.userInfo.InfoUseCase
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingScreenViewModel @Inject constructor(
    private val infoUseCase: InfoUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val refreshUseCase: RefreshTokenUseCase,
) : ViewModel() {

    private val _state: MutableState<LoadingState> = mutableStateOf(LoadingState.Loading)
    var state: State<LoadingState> = _state

    private var scheduleType = ""
    private var dataId = ""
    private var data = ""

    private var twoRoles = false
    private var caughtException = false
    private var refreshCaughtException = false

    private fun defineUser(userInfo: UserInfoDto) {
        if (userInfo.teacherId != null && userInfo.group != null) {
            twoRoles = true
        } else if (userInfo.teacherId != null) {
            scheduleType = "TEACHER"
            dataId = userInfo.teacherId.id
            data = userInfo.teacherId.name
        } else if (userInfo.group != null) {
            scheduleType = "STUDENT"
            dataId = userInfo.group.toString()
        }
    }

    private fun authNavigate(navController: NavController, userData: UserInfoDto) {
        if (!twoRoles) {
            navController.navigate(
                Screen.MainScreen.passScheduleInfo(
                    type = scheduleType,
                    dataId = dataId,
                    data = data
                )
            ) {
                popUpTo(Screen.LoadingScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(
                Screen.ChoiceScreen.passScheduleInfo(
                    studentData = userData.group!!.toString(),
                    teacherId = userData.teacherId!!.id,
                    teacherName = userData.teacherId.name
                )
            ) {
                popUpTo(Screen.LoadingScreen.route) { inclusive = true }
            }
        }
    }

    private fun withoutAuthNavigate(navController: NavController) {
        navController.navigate(Screen.SignInScreen.route) {
            popUpTo(Screen.LoadingScreen.route) { inclusive = true }
        }
    }

    private fun refreshToken(context: Context, navController: NavController) {

        viewModelScope.launch {
            while (!refreshCaughtException) try {
                val token = refreshUseCase(context = context)

                val saveTokenUseCase = SaveTokenUseCase(context)
                saveTokenUseCase.execute(token)

                caughtException = false
                getUserData(context, navController)
                refreshCaughtException = true
            } catch (rethrow: CancellationException) {
                refreshCaughtException = true
                throw rethrow
            } catch (ex: Exception) {
                refreshCaughtException = true
                when (ex.message) {
                    "HTTP 400 Bad Request" -> withoutAuthNavigate(navController)
                }
            }
        }
    }


    private fun getUserData(context: Context, navController: NavController) {
        viewModelScope.launch {
            while (!caughtException) try {
                val userData = infoUseCase(context = context)
                defineUser(userData)

                authNavigate(navController, userData)
                caughtException = true
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                caughtException = true
                when (ex.message) {
                    "HTTP 401 Unauthorized" -> refreshToken(context, navController)
                }
            }
        }
    }


    fun checkAuthorization(
        context: Context,
        navController: NavController
    ) {
        val token = getTokenUseCase(context = context)
        Log.d("token", token.accessToken.toString())
        Log.d("token", token.refreshToken.toString())

        if (token.accessToken.isNullOrEmpty() || token.refreshToken.isNullOrEmpty()) {
            withoutAuthNavigate(navController)
        }
        else {
            getUserData(context, navController)
        }
    }
}