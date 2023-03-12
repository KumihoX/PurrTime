package com.example.timetablemobile.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.timetablemobile.data.remote.dto.TokenResponse

class SharedPreferencesStorage(context: Context) : TokenStorage {

    companion object {
        const val ENCRYPTED_SHARED_PREFS_NAME = "encryptedSharedPreferences"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        ENCRYPTED_SHARED_PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    override fun saveToken(token: TokenResponse) {
        sharedPreferences.edit()
            .putString(TokenStorage.TOKEN_KEY, token.accessToken)
            .putString(TokenStorage.REFRESH_TOKEN_KEY, token.refreshToken)
            .apply()
    }

    override fun getToken(): TokenResponse {
        return TokenResponse(
            sharedPreferences.getString(
                TokenStorage.TOKEN_KEY, ""
            ).toString(),
            sharedPreferences.getString(
                TokenStorage.REFRESH_TOKEN_KEY, ""
            ).toString()
        )
    }

    override fun deleteToken() {
        sharedPreferences.edit()
            .remove(TokenStorage.TOKEN_KEY)
            .remove(TokenStorage.REFRESH_TOKEN_KEY)
            .apply()
    }
}