package com.example.tbc_collaboration_2025.presentation.screen.event_hub

import com.example.tbc_collaboration_2025.domain.error.AppError

interface EventHubContract {
    data class State(
        val errorCode: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object OnLogOutClicked : Event
    }

    sealed interface SideEffect {
        data object NavigateToSignIn : SideEffect
    }
}
