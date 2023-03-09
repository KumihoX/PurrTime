package com.example.timetablemobile.domain.model

import java.time.LocalDate

data class Lesson(
    val type: LessonTypeEnum,
    val subject: String,
    val cabinetName: String,
    val teacher: String,
    val time: String,
    val groups: String,
    val date: LocalDate
)
