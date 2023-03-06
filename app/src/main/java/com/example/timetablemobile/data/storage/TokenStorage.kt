package com.example.timetablemobile.data.storage

import com.example.timetablemobile.data.remote.dto.TokenResponse

interface TokenStorage {
    companion object {
        const val TOKEN_KEY = "userToken"
        const val EMPTINESS_KEY = "empty"
    }

    fun saveToken(token: TokenResponse)

    fun getToken(): TokenResponse

    fun deleteToken()
}