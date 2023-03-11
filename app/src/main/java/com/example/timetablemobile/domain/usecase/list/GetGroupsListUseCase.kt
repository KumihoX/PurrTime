package com.example.timetablemobile.domain.usecase.list

import com.example.timetablemobile.data.remote.dto.GroupListDto
import com.example.timetablemobile.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupsListUseCase @Inject constructor(
    private val repository: GroupRepository
) {

    suspend operator fun invoke(): GroupListDto =
        repository.getList()

}