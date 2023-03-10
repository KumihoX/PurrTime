package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Cabinet
import com.example.timetablemobile.domain.model.GenericModel

data class CabinetListDto(
    val cabinets: List<CabinetDto>
)

fun CabinetListDto.toCabinetList(): List<Cabinet> {
    return cabinets.map { it.toCabinet() }
}

fun CabinetListDto.toGenericModelList(): List<GenericModel> {
    return cabinets.map { it.toGenericModel() }
}