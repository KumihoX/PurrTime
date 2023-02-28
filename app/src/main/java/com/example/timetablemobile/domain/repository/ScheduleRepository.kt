package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.ScheduleDto

interface ScheduleRepository {

    suspend fun getGroupSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto

    suspend fun getClassroomSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): ScheduleDto

    suspend fun getTeacherSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto

}