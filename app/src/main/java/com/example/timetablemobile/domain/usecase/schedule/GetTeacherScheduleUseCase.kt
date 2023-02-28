package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetTeacherScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {

    suspend operator fun invoke(id: String, startsAt: String, endsAt: String): ScheduleDto =
        repository.getTeacherSchedule(id, startsAt, endsAt)

}