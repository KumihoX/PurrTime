package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.ClassroomApi
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(
    private val api: ClassroomApi
) : ClassroomRepository {

    override suspend fun getSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getSchedule(number, startsAt, endsAt)
    }

}