package com.example.timetablemobile.data.repository

import com.example.timetablemobile.data.remote.AuthApi
import com.example.timetablemobile.data.remote.dto.LessonListDto
import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.remote.dto.UserInfoDto
import com.example.timetablemobile.domain.repository.AuthRepository
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(body: LoginDto): TokenResponse {
        return api.login(body)
    }

    override suspend fun logout(token: TokenResponse) {
        return api.logout(token.accessToken.toString())
    }

    override suspend fun refresh(token: TokenResponse): TokenResponse {
        return api.refreshToken(token.refreshToken.toString())
    }


    override suspend fun getInfo(token: TokenResponse): UserInfoDto {
        return api.getInfo(token.accessToken.toString())
    }

}