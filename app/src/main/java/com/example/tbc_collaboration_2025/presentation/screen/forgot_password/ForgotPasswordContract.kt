package com.example.tbc_collaboration_2025.presentation.screen.forgot_password


interface ForgotPasswordContract {
    data class State(val email: String = "", val isLoading: Boolean = false)

    sealed interface Event {
        data class OnEmailChanged(val value: String) : Event
        data object OnSendResetLinkClicked : Event
        data object OnBackToSignInClicked : Event
    }

    sealed interface SideEffect {
        data object NavigateToSignIn : SideEffect
    }
}
