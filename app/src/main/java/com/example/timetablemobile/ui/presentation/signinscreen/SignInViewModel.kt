package com.example.timetablemobile.ui.presentation.signinscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetablemobile.common.Resource
import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.domain.usecase.login.LoginUseCase
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state: MutableState<SignInScreenState> = mutableStateOf(SignInScreenState.Initial)
    var state: State<SignInScreenState> = _state

    private val _login = mutableStateOf("")
    var login: State<String> = _login

    private val _password = mutableStateOf("")
    var password: State<String> = _password

    private val _fieldsState = mutableStateOf(false)
    var fieldsState: State<Boolean> = _fieldsState

    private fun checkingFields() {
        _fieldsState.value = !(login.value.isNullOrEmpty()
                || password.value.isNullOrEmpty())
    }

    fun login(navController: NavController) {

        val userData = LoginDto(
            login = _login.value,
            password = _password.value
        )

        viewModelScope.launch {
            _state.value = SignInScreenState.Loading

            try {
                val token = loginUseCase(userData)
                _state.value = SignInScreenState.Content(token)
                navController.navigate(Screen.MainScreen.route)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SignInScreenState.Error(
                    when (ex.message) {
                        "HTTP 400 Bad Request" -> "Введенные данные неверны"
                        else -> "Что-то пошло не так"
                    })
            }
        }
    }


    fun onLoginChange(newLogin: String) {
        _login.value = newLogin
        checkingFields()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        checkingFields()
    }
}
