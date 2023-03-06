package com.example.timetablemobile

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.domain.usecase.logout.LogoutUseCase
import com.example.timetablemobile.domain.usecase.userInfo.InfoUseCase
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.ui.presentation.mainscreen.MainState
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val infoUseCase: InfoUseCase
) : ViewModel() {
    private val _state: MutableState<MainActivityState> = mutableStateOf(MainActivityState.Loading)
    var state: State<MainActivityState> = _state

    private val _startScreen = mutableStateOf(Screen.SignInScreen.route)
    var startScreen: State<String> = _startScreen

    fun checkAuthorization(
        context: Context
    ) {
        viewModelScope.launch {
            try {
                infoUseCase(context = context)
                _startScreen.value = Screen.MainScreen.route
                _state.value = MainActivityState.Initial
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                when (ex.message) {
                    "HTTP 401 Unauthorized" -> _state.value = MainActivityState.Initial
                    else -> _state.value = MainActivityState.Initial
                }
            }
        }
    }
}