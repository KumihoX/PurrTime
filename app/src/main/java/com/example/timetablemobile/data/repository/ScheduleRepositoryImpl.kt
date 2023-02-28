package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.ScheduleApi
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val api: ScheduleApi
) : ScheduleRepository {

    override suspend fun getGroupSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getGroupSchedule(id, startsAt, endsAt)
    }

    override suspend fun getClassroomSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getClassroomSchedule(number, startsAt, endsAt)
    }

    override suspend fun getTeacherSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getTeacherSchedule(id, startsAt, endsAt)
    }

}