package com.example.tbc_collaboration_2025.presentation.screen.sign_up

import com.example.tbc_collaboration_2025.domain.error.AppError

interface SignUpContract {
    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isAgreed: Boolean = false,
        val errorCode: AppError? = null,
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data class OnFirstNameChanged(val value: String) : Event
        data class OnLastNameChanged(val value: String) : Event
        data class OnEmailChanged(val value: String) : Event
        data class OnPasswordChanged(val value: String) : Event
        data class OnConfirmPasswordChanged(val value: String) : Event
        data class OnAgreementChanged(val isChecked: Boolean) : Event
        data object OnSignUpClicked : Event
        data object OnSignInClicked : Event
    }

    sealed interface SideEffect {
        data class ShowError(val errorCode: AppError) : SideEffect
        data object NavigateToEventHub : SideEffect
        data object NavigateToSignIn : SideEffect
    }
}
