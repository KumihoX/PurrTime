package com.example.timetablemobile.domain.usecase.userInfo

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.data.remote.dto.UserInfoDto
import com.example.timetablemobile.domain.repository.AuthRepository
import com.example.timetablemobile.domain.usecase.token.GetTokenFromLocalStorageUseCase
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(context: Context): UserInfoDto {
        val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(context)
        val token = getTokenFromLocalStorageUseCase.execute()

        val bearerToken = TokenResponse("Bearer ${token.accessToken}")

        return repository.getInfo(bearerToken)
    }
}