package com.example.timetablemobile.ui.presentation.signinscreen

import com.example.timetablemobile.data.remote.dto.TokenResponse


sealed interface SignInState {

    object Initial : SignInState

    object Loading : SignInState

    data class Content(val token: TokenResponse) : SignInState

    data class Error(val error: String) : SignInState
}

