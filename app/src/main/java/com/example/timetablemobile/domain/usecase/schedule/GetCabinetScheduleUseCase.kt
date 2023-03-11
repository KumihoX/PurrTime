package com.example.timetablemobile.domain.usecase.schedule

import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.domain.repository.CabinetRepository
import javax.inject.Inject

class GetCabinetScheduleUseCase @Inject constructor(
    private val repository: CabinetRepository
) {

    suspend operator fun invoke(number: Int, startsAt: String, endsAt: String): LessonListDto =
        repository.getSchedule(number, startsAt, endsAt)

}