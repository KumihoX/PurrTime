package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.remote.dto.UserInfoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("auth/login/mobile")
    suspend fun login(@Body body: LoginDto): TokenResponse

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String)

    @POST("auth/refresh")
    suspend fun refreshToken(@Query("token") refreshToken: String): TokenResponse

    @GET("users/me")
    suspend fun getInfo(@Header("Authorization") token: String): UserInfoDto
}