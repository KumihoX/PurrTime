package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.GenericModel
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

fun TeacherDto.toGenericModel(): GenericModel {
    return GenericModel(
        id = id,
        name = name
    )
}