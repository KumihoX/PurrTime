package com.example.timetablemobile.data.remote.dto

data class LessonDto(
    val type: Int,
    val subject: String,
    val cabinet: CabinetDto,
    val teacher: String,
    val timeslot: TimeslotDto,
    val groupsNum: List<Int>,
    val data: String
)

fun LessonDto.toLesson(): Lesson {
    return Lesson(

    )
}
