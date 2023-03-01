package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse

//Нужно ли тут писать @Body
interface AuthRepository {
    suspend fun login(body: LoginDto): TokenResponse

    suspend fun logout()
}