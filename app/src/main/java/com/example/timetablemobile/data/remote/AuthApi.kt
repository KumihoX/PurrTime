package com.example.timetablemobile.data.remote

import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login/mobile")
    suspend fun login(@Body body: LoginDto): TokenResponse

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String)

    @GET("users/me")
    suspend fun getInfo(@Header("Authorization") token: String)
}