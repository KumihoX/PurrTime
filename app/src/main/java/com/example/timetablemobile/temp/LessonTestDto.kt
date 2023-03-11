package com.example.timetablemobile.temp

data class LessonTestDto(
    val type: TestLessonType,
    val cabinet: CabinetTestDto,
    val date: String,
    val groups: List<Int>,
    val subject: String,
    val teacher: String,
    val timeslot: TimeslotTestDto
)