package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Lesson
import com.example.timetablemobile.domain.model.LessonType

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

    val lessonType = when(type) {
        0 -> LessonType.Lection
        1 -> LessonType.Seminar
        2 -> LessonType.Practice
        3 -> LessonType.Lab
        4 -> LessonType.IndividualLesson
        5 -> LessonType.ControlWork
        6 -> LessonType.Consultation
        7 -> LessonType.Booking
        else -> LessonType.ContactWork
    }

    val parsedStart = timeslot.startsAt.substringAfter("T").split(":")
    val starts = parsedStart[0] + ":" + parsedStart[1]

    val parsedEnd = timeslot.endsAt.substringAfter("T").split(":")
    val ends = parsedEnd[0] + ":" + parsedEnd[1]

    var groups = ""
    for(group in groupsNum) {
        groups = "$groups, $group"
    }

    return Lesson(
        type = lessonType,
        subject = subject,
        cabinetName = cabinet.number.toString() + " " + cabinet.name,
        teacher = teacher,
        startsAt = starts,
        endsAt = ends,
        groups = groups
    )
}