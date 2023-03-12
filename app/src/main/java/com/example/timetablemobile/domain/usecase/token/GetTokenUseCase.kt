package com.example.timetablemobile.domain.usecase.token

import android.content.Context
import com.example.timetablemobile.data.remote.dto.TokenResponse
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
) {
    operator fun invoke(context: Context): TokenResponse {
        val getTokenFromLocalStorageUseCase = GetTokenFromLocalStorageUseCase(context)
        return getTokenFromLocalStorageUseCase.execute()
    }
}