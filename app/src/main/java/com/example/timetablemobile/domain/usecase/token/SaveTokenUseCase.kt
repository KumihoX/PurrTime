package com.example.timetablemobile.domain.usecase.token

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.repository.TokenRepository

class SaveTokenUseCase(private val context: Context) {

    private val tokenRepository by lazy {
        TokenRepository(context)
    }

    fun execute(token: TokenResponse) {
        tokenRepository.saveTokenToLocalStorage(token)
    }
}