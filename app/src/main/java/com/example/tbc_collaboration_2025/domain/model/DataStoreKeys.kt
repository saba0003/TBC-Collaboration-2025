package com.example.tbc_collaboration_2025.domain.model

// TODO: android logic inside domain!!!
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    private const val AUTH_TOKEN = "auth_token"
    val TOKEN_KEY = stringPreferencesKey(name = AUTH_TOKEN)
}
