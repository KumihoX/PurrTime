package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.data.remote.dto.TeacherListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeacherApi {

    @GET("teachers/{id}/schedule")
    suspend fun getSchedule(
        @Path("id") id: String,
        @Query("startsAt") startsAt: String,
        @Query("endsAt") endsAt: String
    ): LessonListDto

    @GET("teachers")
    suspend fun getList(): TeacherListDto

}