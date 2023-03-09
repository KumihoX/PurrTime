package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.data.remote.dto.TeacherListDto

interface TeacherRepository {

    suspend fun getSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): LessonListDto

    suspend fun getList(): TeacherListDto

}