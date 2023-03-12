package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Teacher

data class TeacherDto(
    val id: String,
    val name: String = ""
)

fun TeacherDto.toTeacher(): Teacher {
    return Teacher(
        id = id,
        name = name
    )
}