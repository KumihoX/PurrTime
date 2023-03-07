package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.GroupsDto
import com.example.timetablemobile.data.remote.dto.ScheduleDto

interface GroupRepository {

    suspend fun getSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto

    suspend fun getList(): GroupsDto

}