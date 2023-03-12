package com.example.timetablemobile.data.storage

import com.example.timetablemobile.data.remote.dto.TokenResponse

interface TokenStorage {
    companion object {
        const val TOKEN_KEY = "userToken"
        const val REFRESH_TOKEN_KEY = "refreshUserToken"
    }

    fun saveToken(token: TokenResponse)

    fun getToken(): TokenResponse

    fun deleteToken()
}