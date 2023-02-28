package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.GroupApi
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val api: GroupApi
) : GroupRepository {

    override suspend fun getSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getSchedule(id, startsAt, endsAt)
    }

}