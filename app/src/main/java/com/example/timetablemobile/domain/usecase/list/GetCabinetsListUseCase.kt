package com.example.timetablemobile.domain.usecase.list

import com.example.timetablemobile.data.remote.dto.CabinetsDto
import com.example.timetablemobile.domain.repository.CabinetRepository
import javax.inject.Inject

class GetCabinetsListUseCase @Inject constructor(
    private val repository: CabinetRepository
) {

    suspend operator fun invoke(): CabinetsDto =
        repository.getList()

}