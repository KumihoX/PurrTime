package com.example.timetablemobile.domain.model

data class WeeklySchedule(
    val monday: List<Lesson> = emptyList(),
    val tuesday: List<Lesson> = emptyList(),
    val wednesday: List<Lesson> = emptyList(),
    val thursday : List<Lesson> = emptyList(),
    val friday : List<Lesson> = emptyList(),
    val saturday : List<Lesson> = emptyList(),
    val sunday : List<Lesson> = emptyList(),
)