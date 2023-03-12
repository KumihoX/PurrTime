package com.example.timetablemobile.domain.repository

import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.remote.dto.UserInfoDto

interface AuthRepository {
    suspend fun login(body: LoginDto): TokenResponse

    suspend fun logout(token: TokenResponse)

    suspend fun refresh(token: TokenResponse): TokenResponse

    suspend fun getInfo(token: TokenResponse): UserInfoDto
}