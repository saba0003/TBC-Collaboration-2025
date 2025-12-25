package com.example.tbc_collaboration_2025.presentation.screen.event_hub

import androidx.lifecycle.viewModelScope
import com.example.tbc_collaboration_2025.domain.model.DataStoreKeys.TOKEN_KEY
import com.example.tbc_collaboration_2025.domain.use_case.data_store.RemovePreferencesUseCase
import com.example.tbc_collaboration_2025.presentation.common.BaseViewModel
import com.example.tbc_collaboration_2025.presentation.screen.event_hub.EventHubContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventHubViewModel @Inject constructor(private val removePreferencesUseCase: RemovePreferencesUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {

    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            Event.OnLogOutClicked -> onLogout()
        }
    }

    private fun onLogout() {
        viewModelScope.launch {
            removePreferencesUseCase(keys = listOf(TOKEN_KEY))
            emitSideEffect(sideEffect = SideEffect.NavigateToSignIn)
        }
    }
}
