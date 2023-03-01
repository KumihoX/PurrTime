package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.temp.TestLessonType

data class LessonDto(
    //val type: String,
    val name: String,
    //val cabinet: CabinetDto,
    val cabinet: String,
    //val date: String,
    //val groups: List<Int>,
    val group: List<Int>,
    //val subject: String,
    val teacher: String,
    //val timeslot: TimeslotDto
    val start: String,
    val end: String
)
