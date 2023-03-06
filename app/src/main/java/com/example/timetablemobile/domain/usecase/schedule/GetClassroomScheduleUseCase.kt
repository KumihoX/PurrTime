package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.ClassroomRepository
import javax.inject.Inject

class GetClassroomScheduleUseCase @Inject constructor(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke(number: Int, startsAt: String, endsAt: String): ScheduleDto =
        repository.getSchedule(number, startsAt, endsAt)

}