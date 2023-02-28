package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

    @GET("groups/{id}/schedule")
    suspend fun getGroupSchedule(
        @Path("id") id: String,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): ScheduleDto

    @GET("classrooms/{number}/schedule")
    suspend fun getClassroomSchedule(
        @Path("number") number: Int,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): ScheduleDto

    @GET("teachers/{id}/schedule")
    suspend fun getTeacherSchedule(
        @Path("id") id: String,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): ScheduleDto

}