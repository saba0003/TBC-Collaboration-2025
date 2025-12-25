package com.example.tbc_collaboration_2025.domain.use_case.data_store

// TODO: Android logic here!!!
import androidx.datastore.preferences.core.Preferences
import com.example.tbc_collaboration_2025.data.local.DataStoreManager
import javax.inject.Inject

class RemovePreferencesUseCase @Inject constructor(private val preferencesRepository: DataStoreManager) {

    suspend operator fun invoke(keys: List<Preferences.Key<*>>) =
        preferencesRepository.removePreferences(keys = keys)

}
