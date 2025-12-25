package com.example.tbc_collaboration_2025.domain.use_case.data_store

// TODO: Android logic here!!!
import androidx.datastore.preferences.core.Preferences
import com.example.tbc_collaboration_2025.data.local.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferenceUseCase @Inject constructor(private val preferencesRepository: DataStoreManager) {

    suspend operator fun <T> invoke(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        preferencesRepository.getPreference(key = key, defaultValue = defaultValue)

}
