package com.example.tbc_collaboration_2025.data.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

// TODO: In Nika's code, getPreference() function is suspend
interface DataStoreManager {
    fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> setPreference(key: Preferences.Key<T>, value: T)
    suspend fun removePreferences(keys: List<Preferences.Key<*>>)
}
