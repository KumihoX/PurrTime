package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.CabinetApi
import com.example.timetablemobile.data.remote.dto.CabinetsDto
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import com.example.timetablemobile.domain.repository.CabinetRepository
import javax.inject.Inject

class CabinetRepositoryImpl @Inject constructor(
    private val api: CabinetApi
) : CabinetRepository {

    override suspend fun getSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): ScheduleDto {
        return api.getSchedule(number, startsAt, endsAt)
    }

    override suspend fun getList(): CabinetsDto {
        return api.getList()
    }

}