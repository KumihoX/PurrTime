package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.CabinetsDto
import com.example.timetablemobile.data.remote.dto.ScheduleDto

interface CabinetRepository {

    suspend fun getSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): ScheduleDto

    suspend fun getList(): CabinetsDto

}