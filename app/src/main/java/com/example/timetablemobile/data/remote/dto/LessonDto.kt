package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Lesson
import com.example.timetablemobile.domain.model.LessonTypeEnum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class LessonDto(
    val id: String,
    val lesson: LessonShortDto,
    val timeslot: TimeslotDto,
    val date: String
)

fun LessonDto.toLesson(): Lesson {
    val lessonType = when(lesson.type) {
        "LECTURE" -> LessonTypeEnum.Lection
        "SEMINAR" -> LessonTypeEnum.Seminar
        "PRACTICE" -> LessonTypeEnum.Practice
        "LABWORK" -> LessonTypeEnum.Lab
        "INDIVIDUAL_LESSON" -> LessonTypeEnum.IndividualLesson
        "CONTROL_POINT" -> LessonTypeEnum.ControlWork
        "CONSULTATION" -> LessonTypeEnum.Consultation
        "RESERVATION" -> LessonTypeEnum.Booking
        else -> LessonTypeEnum.ContactWork
    }

    val parsedStart = timeslot.startAt.substringAfter("T").split(":")
    val starts = parsedStart[0] + ":" + parsedStart[1]

    val parsedEnd = timeslot.endsAt.substringAfter("T").split(":")
    val ends = parsedEnd[0] + ":" + parsedEnd[1]

    var groups = ""
    if (lesson.groups.isNotEmpty()) {
        groups = lesson.groups[0].toString()

        for (i in 1 until lesson.groups.size) {
            val newGroup = lesson.groups[i]
            groups = "$groups, $newGroup"
        }
    }

    val currFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val parsedDate = LocalDate.parse(date, currFormatter)

    return Lesson(
        type = lessonType,
        subject = lesson.subject,
        cabinet = lesson.cabinet.toCabinet(),
        teacher = lesson.teacher.toTeacher(),
        time = "$starts - $ends",
        groups = groups,
        date = parsedDate
    )
}