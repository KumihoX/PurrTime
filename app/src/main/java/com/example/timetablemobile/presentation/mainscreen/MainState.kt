package com.example.timetablemobile.presentation.mainscreen

sealed interface MainState {

    object Initial : MainState

    object Loading : MainState

    // Schedule will be added later
    //data class Content(val schedule: Schedule) : MainState

    data class Error(val error: String) : MainState
}