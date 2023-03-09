package com.example.timetablemobile.ui.presentation.mainscreen

import com.example.timetablemobile.data.remote.dto.ScheduleDto

sealed interface MainState {

    object Initial : MainState

    object Loading : MainState

    // WeeklySchedule will be added later
    data class Content(val schedule: ScheduleDto) : MainState

    data class Error(val error: String) : MainState
}