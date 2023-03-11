package com.example.timetablemobile.domain.usecase.login

import com.example.timetablemobile.data.remote.dto.LoginDto
import com.example.timetablemobile.data.remote.dto.TokenResponse
import com.example.timetablemobile.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(userData: LoginDto): TokenResponse = repository.login(userData)
}
