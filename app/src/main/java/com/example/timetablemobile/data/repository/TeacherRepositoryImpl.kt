package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.TeacherApi
import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.data.remote.dto.TeacherListDto
import com.example.timetablemobile.domain.repository.TeacherRepository
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val api: TeacherApi
) : TeacherRepository {

    override suspend fun getSchedule(
        id: String,
        startsAt: String,
        endsAt: String
    ): LessonListDto {
        return api.getSchedule(id, startsAt, endsAt)
    }

    override suspend fun getList(): TeacherListDto {
        return api.getList()
    }

}