package com.example.timetablemobile

import com.example.timetablemobile.data.remote.dto.ScheduleDto

sealed interface MainActivityState{

    object Initial : MainActivityState

    object Loading : MainActivityState
}