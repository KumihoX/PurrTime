package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.AuthApi
import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(body: LoginDto): TokenResponse {
        return api.login(body)
    }

    override suspend fun logout() {
        return api.logout()
    }

}