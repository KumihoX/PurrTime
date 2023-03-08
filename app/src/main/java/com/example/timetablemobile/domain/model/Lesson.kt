package com.example.timetablemobile.domain.model

data class Lesson(
    val type: LessonType,
    val subject: String,
    val cabinetName: String,
    val teacher: String,
    val startsAt: String,
    val endsAt: String,
    val groups: String
)
