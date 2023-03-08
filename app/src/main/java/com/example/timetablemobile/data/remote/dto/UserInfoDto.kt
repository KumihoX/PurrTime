package com.example.timetablemobile.data.remote.dto

data class UserInfoDto (
    val id: String,
    val login: String? = "",
    val roles: List<Int>,
    val teacherId: String? = "",
    val group: Int? = null
)