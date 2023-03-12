package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.Cabinet

data class CabinetListDto(
    val cabinets: List<CabinetDto> = emptyList()
)

fun CabinetListDto.toCabinetList(): List<Cabinet> {
    return cabinets.map { it.toCabinet() }
}