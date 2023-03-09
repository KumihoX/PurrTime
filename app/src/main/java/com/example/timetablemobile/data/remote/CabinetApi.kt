package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.CabinetListDto
import com.example.timetablemobile.data.remote.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CabinetApi {

    @GET("classrooms/{number}/schedule")
    suspend fun getSchedule(
        @Path("number") number: Int,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): ScheduleDto

    @GET("cabinets")
    suspend fun getList(): CabinetListDto

}