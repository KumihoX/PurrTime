package com.example.timetablemobile.ui.presentation.signinscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetablemobile.common.Resource
import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = mutableStateOf(SignInState())
    var state: State<SignInState> = _state

    private val _login = mutableStateOf("")
    var login: State<String> = _login

    private val _password = mutableStateOf("")
    var password: State<String> = _password

    private val _correct = mutableStateOf(true)
    var correct: State<Boolean> = _correct

    private val _fieldsState = mutableStateOf(false)
    var fieldsState: State<Boolean> = _fieldsState

    private fun checkingFields() {
        loginIsCorrect()
        _fieldsState.value = !(login.value.isNullOrEmpty()
                || password.value.isNullOrEmpty()
                || !_correct.value)
    }

    private fun loginIsCorrect() {
        _correct.value =
            _login.value.let { android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true
    }

    fun login() {

        val userData = LoginDto(
            login = _login.value,
            password = _password.value
        )

        loginUseCase(userData).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = SignInState(token = result.data)
                }
                is Resource.Error -> {
                    _state.value = SignInState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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
