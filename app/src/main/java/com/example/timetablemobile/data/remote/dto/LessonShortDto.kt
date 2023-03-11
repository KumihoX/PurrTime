package com.example.timetablemobile.data.remote.dto

data class LessonShortDto(
    val id: String,
    val teacher: TeacherDto,
    val groups: List<Int>,
    val type: String,
    val subject: String,
    val cabinet: CabinetDto,
)