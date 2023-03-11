package com.example.timetablemobile.data.repository

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.storage.SharedPreferencesStorage

class TokenRepository(
    context: Context
) {

    private val sharedPreferencesStorage by lazy {
        SharedPreferencesStorage(context)
    }

    fun saveTokenToLocalStorage(token: TokenResponse) {
        sharedPreferencesStorage.saveToken(token)
    }

    fun getTokenFromLocalStorage(): TokenResponse {
        return sharedPreferencesStorage.getToken()
    }

    fun deleteTokenFromLocalStorage() {
        sharedPreferencesStorage.deleteToken()
    }
}