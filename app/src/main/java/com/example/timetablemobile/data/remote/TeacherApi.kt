package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeacherApi {

    @GET("teachers/{id}/schedule")
    suspend fun getSchedule(
        @Path("id") id: String,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): ScheduleDto

}