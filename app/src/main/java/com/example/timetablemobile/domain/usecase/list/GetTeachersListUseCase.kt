package com.example.timetablemobile.domain.usecase.list

import com.example.timetablemobile.data.remote.dto.TeacherListDto
import com.example.timetablemobile.domain.repository.TeacherRepository
import javax.inject.Inject

class GetTeachersListUseCase @Inject constructor(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke(): TeacherListDto =
        repository.getList()

}