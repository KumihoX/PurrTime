package com.example.timetablemobile.presentation.signinscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _login = mutableStateOf("")
    var login: State<String> = _login

    private val _password = mutableStateOf("")
    var password: State<String> = _password

    fun onLoginChange(newLogin: String) {
        _login.value = newLogin
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }
}