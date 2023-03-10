package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.GenericModel
import com.example.timetablemobile.domain.model.Teacher

data class TeacherListDto(
    val teachers: List<TeacherDto>
)

fun TeacherListDto.toTeacherList(): List<Teacher> {
    return teachers.map { it.toTeacher() }
}

fun TeacherListDto.toGenericModelList(): List<GenericModel> {
    return teachers.map { it.toGenericModel() }
}