package com.example.timetablemobile.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timetablemobile.domain.model.WeeklySchedule

data class ScheduleDto(
    val schedule: List<LessonDto>
)

@RequiresApi(Build.VERSION_CODES.O)
fun ScheduleDto.toWeeklySchedule(): WeeklySchedule {

    val lessons = schedule.map { it.toLesson() }

    for (lesson in lessons) {
        when(lesson) {

        }
    }

    return WeeklySchedule(

    )
}