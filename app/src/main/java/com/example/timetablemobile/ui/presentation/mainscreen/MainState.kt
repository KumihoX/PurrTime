package com.example.timetablemobile.ui.presentation.mainscreen

import com.example.timetablemobile.domain.model.WeeklySchedule

sealed interface MainState {

    object Initial : MainState

    object Loading : MainState

    data class Content(val schedule: WeeklySchedule) : MainState

    data class Error(val error: String) : MainState
}