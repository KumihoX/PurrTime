package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.domain.repository.TeacherRepository
import javax.inject.Inject

class GetTeacherScheduleUseCase @Inject constructor(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke(id: String, startsAt: String, endsAt: String): LessonListDto =
        repository.getSchedule(id, startsAt, endsAt)

}