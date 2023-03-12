package com.example.timetablemobile.domain.usecase.token

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.repository.TokenRepository

class GetTokenFromLocalStorageUseCase(
    private val context: Context
    ) {

    private val tokenRepositoryImpl by lazy {
        TokenRepository(context)
    }

    fun execute(): TokenResponse {
        return tokenRepositoryImpl.getTokenFromLocalStorage()
    }
}