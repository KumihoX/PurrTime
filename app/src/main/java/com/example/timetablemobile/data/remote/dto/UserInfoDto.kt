package com.example.timetablemobile.data.remote.dto

data class UserInfoDto(
    val id: String,
    val login: String? = "",
    val roles: List<String>,
    val teacherId: TeacherDto? = null,
    val group: Int? = null
)