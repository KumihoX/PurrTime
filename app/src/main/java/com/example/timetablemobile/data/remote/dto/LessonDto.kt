package com.example.timetablemobile.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
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

    val parsedStart = timeslot.startsAt.substringAfter("T").split(":")
    val starts = parsedStart[0] + ":" + parsedStart[1]

    val parsedEnd = timeslot.endsAt.substringAfter("T").split(":")
    val ends = parsedEnd[0] + ":" + parsedEnd[1]

    var groups = ""
    for(group in lesson.groups) {
        groups = "$groups, $group"
    }

    val currFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val newFormatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy")
    val parsedDate = LocalDate.parse(date, currFormatter)

    return Lesson(
        type = lessonType,
        subject = lesson.subject,
        cabinetName = lesson.cabinet.number.toString() + lesson.cabinet.name,
        teacher = lesson.teacher,
        time = "$starts - $ends",
        groups = groups,
        date = parsedDate
    )
}