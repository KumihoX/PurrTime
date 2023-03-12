package com.example.timetablemobile.domain.usecase.token

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.domain.repository.AuthRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(context: Context): TokenResponse {
        val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(context)
        val token = getTokenFromLocalStorageUseCase.execute()

        return repository.refresh(token)
    }
}