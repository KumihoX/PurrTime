package com.example.timetablemobile.domain.usecase.logout

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.repository.TokenRepository
import com.example.timetablemobile.domain.repository.AuthRepository
import com.example.timetablemobile.domain.usecase.token.GetTokenFromLocalStorageUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(context: Context)
    {
        val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(context)
        val token = getTokenFromLocalStorageUseCase.execute()
        val bearerToken = TokenResponse("Bearer ${token.accessToken}")

        val tokenRepository = TokenRepository(context)
        repository.logout(bearerToken)
        tokenRepository.deleteTokenFromLocalStorage()
    }
}