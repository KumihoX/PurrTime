package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Cabinet

data class CabinetDto(
    val number: Int,
    val name: String = ""
)

fun CabinetDto.toCabinet(): Cabinet {
    return if (name.isNullOrBlank())
        Cabinet(
            id = number,
            name = number.toString()
        )
    else
        Cabinet(
            id = number,
            name = "$number $name"
        )
}