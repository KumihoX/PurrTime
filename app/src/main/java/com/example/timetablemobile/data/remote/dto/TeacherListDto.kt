package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Teacher

data class TeacherListDto(
    val teachers: List<TeacherDto> = emptyList()
)

fun TeacherListDto.toTeacherList(): List<Teacher> {
    return teachers.map { it.toTeacher() }
}