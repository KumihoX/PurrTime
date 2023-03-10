package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.GroupListDto
import com.example.timetablemobile.data.remote.dto.LessonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupApi {

    @GET("groups/{id}/schedule")
    suspend fun getSchedule(
        @Path("id") id: String,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): LessonListDto

    @GET("groups")
    suspend fun getList(): GroupListDto

}