package com.example.tbc_collaboration_2025.domain.model

// TODO: android logic inside domain!!!
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    private const val USER_TOKEN = "user_token"

    val USER_TOKEN_PREF = stringPreferencesKey(name = USER_TOKEN)
}
