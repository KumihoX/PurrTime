package com.example.timetablemobile.data.remote.dto

data class CabinetsDto(
    val cabinetsList: List<CabinetDto>
)

//нужно ли тут добавлять прям модель отдельную в которой будет только List??
fun CabinetsDto.toCabinetsNumbersList(): List<Int> {
    return cabinetsList.map { it.number }
}