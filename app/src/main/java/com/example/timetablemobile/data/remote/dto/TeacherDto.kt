package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Teacher

data class TeacherDto(
    val id: String,
    val name: String = ""
)

fun TeacherDto.toTeacher(): Teacher {
    return if (name.isNullOrBlank())
        Teacher(
            id = id,
            name = "Unnamed"
        )
    else
        Teacher(
            id = id,
            name = name
        )
}