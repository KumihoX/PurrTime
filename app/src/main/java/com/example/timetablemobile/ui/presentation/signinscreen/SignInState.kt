package com.example.timetablemobile.ui.presentation.signinscreen

import com.example.timetablemobile.data.remote.dto.TokenResponse

data class SignInState(
    val isLoading: Boolean = false,
    val token: TokenResponse? = null,
    val error: String = ""
)
