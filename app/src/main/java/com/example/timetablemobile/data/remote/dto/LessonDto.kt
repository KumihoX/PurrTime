package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.temp.TestLessonType

data class LessonDto(
    val type: String,
    val cabinet: CabinetDto,
    val date: String,
    val groups: List<Int>,
    val subject: String,
    val teacher: String,
    val timeslot: TimeslotDto
)
