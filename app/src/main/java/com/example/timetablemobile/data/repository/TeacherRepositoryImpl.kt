package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.TeacherApi
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.data.remote.dto.TeachersDto
import com.example.timetablemobile.domain.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val api: TeacherApi
) : TeacherRepository {

    override suspend fun getSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getSchedule(id, startsAt, endsAt)
    }

    override suspend fun getList(): TeachersDto {
        return api.getList()
    }

}