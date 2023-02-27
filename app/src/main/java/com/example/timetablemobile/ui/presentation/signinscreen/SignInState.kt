package com.example.timetablemobile.ui.presentation.signinscreen
import com.example.timetablemobile.data.remote.dto.TokenResponse


sealed interface SignInScreenState {

    object Initial : SignInScreenState

    object Loading : SignInScreenState

    data class Content(val token: TokenResponse) : SignInScreenState

    data class Error(val error: String) : SignInScreenState
}

