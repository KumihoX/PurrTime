package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupScheduleUseCase @Inject constructor(
    private val repository: GroupRepository
) {

    suspend operator fun invoke(id: String, startsAt: String, endsAt: String): ScheduleDto =
        repository.getSchedule(id, startsAt, endsAt)

}