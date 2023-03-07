package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.CabinetRepository
import javax.inject.Inject

class GetCabinetScheduleUseCase @Inject constructor(
    private val repository: CabinetRepository
) {

    suspend operator fun invoke(number: Int, startsAt: String, endsAt: String): ScheduleDto =
        repository.getSchedule(number, startsAt, endsAt)

}