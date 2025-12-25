package com.example.tbc_collaboration_2025.presentation.screen.splash

interface SplashContract {
    data class State(val isLoading: Boolean = false)

    sealed interface SideEffect {
        data object NavigateToEventHub : SideEffect
        data object NavigateToSignUp : SideEffect
    }
}
