package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.CabinetListDto
import com.example.timetablemobile.data.remote.dto.LessonListDto

interface CabinetRepository {

    suspend fun getSchedule(
        number: Int,
        startsAt: String,
        endsAt: String
    ): LessonListDto

    suspend fun getList(): CabinetListDto

}