package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Cabinet
import com.example.timetablemobile.domain.model.GenericModel

data class CabinetDto(
    val number: Int,
    val name: String
)

fun CabinetDto.toCabinet(): Cabinet {
    return Cabinet(
        id = number,
        name = "$number $name"
    )
}

fun CabinetDto.toGenericModel(): GenericModel {
    return GenericModel(
        idNumber = number,
        name = name
    )
}