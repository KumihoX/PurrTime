package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.WeeklySchedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class LessonListDto(
    val lessons: List<LessonDto>
)

fun LessonListDto.toWeeklySchedule(): WeeklySchedule {

    val currFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    val sortedLessonsDto = lessons.sortedByDescending { LocalDateTime.parse(it.timeslot.startAt, currFormatter) }.reversed()
    val sortedLessons = sortedLessonsDto.map { it.toLesson() }

    val weeklySchedule = WeeklySchedule()
    for (lesson in sortedLessons) {
        when(lesson.date.dayOfWeek) {
            DayOfWeek.MONDAY -> weeklySchedule.monday.add(lesson)
            DayOfWeek.TUESDAY -> weeklySchedule.tuesday.add(lesson)
            DayOfWeek.WEDNESDAY -> weeklySchedule.wednesday.add(lesson)
            DayOfWeek.THURSDAY -> weeklySchedule.thursday.add(lesson)
            DayOfWeek.FRIDAY -> weeklySchedule.friday.add(lesson)
            DayOfWeek.SATURDAY -> weeklySchedule.saturday.add(lesson)
            DayOfWeek.SUNDAY -> weeklySchedule.sunday.add(lesson)
        }
    }

    return weeklySchedule
}