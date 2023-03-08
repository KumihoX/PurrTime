package com.example.timetablemobile.data.remote.dto

data class TeachersDto(
    val teachersList: List<TeacherDto>
)

fun TeachersDto.getTeacherNamesList(): List<String> {
    return teachersList.map { it.name }
}