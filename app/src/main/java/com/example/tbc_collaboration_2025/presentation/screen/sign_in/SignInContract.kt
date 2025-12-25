package com.example.tbc_collaboration_2025.presentation.screen.sign_in

import com.example.tbc_collaboration_2025.domain.error.AppError

interface SignInContract {
    data class State(
        val email: String = "",
        val password: String = "",
        val rememberMe: Boolean = false,
        val errorCode: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data class OnEmailChanged(val value: String) : Event
        data class OnPasswordChanged(val value: String) : Event
        data class OnRememberMeChanged(val isChecked: Boolean) : Event
        data object OnSignInClicked : Event
        data object OnForgotPasswordClicked : Event
        data object OnSignUpClicked : Event
    }

    sealed interface SideEffect {
        data class ShowError(val errorCode: AppError) : SideEffect
        data object NavigateToEventHub : SideEffect
        data object NavigateToSignUp : SideEffect
        data object NavigateToForgotPassword : SideEffect
    }
}
