package com.example.timetablemobile.data.remote.dto

import com.example.timetablemobile.domain.model.GenericModel

data class GroupListDto(
    val groups: List<Int>
)

fun GroupListDto.toGenericModelList(): List<GenericModel> {
    return groups.map { GenericModel(idNumber = it) }
}