package com.example.tbc_collaboration_2025.presentation.screen.forgot_password

import com.example.tbc_collaboration_2025.presentation.common.BaseViewModel
import com.example.tbc_collaboration_2025.presentation.screen.forgot_password.ForgotPasswordContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    override fun onEvent(event: Event) = with(receiver = event) {
        when (this) {
            is Event.OnEmailChanged -> updateState { copy(email = email) }
            Event.OnSendResetLinkClicked -> Unit // Do nothing for now
            Event.OnBackToSignInClicked -> onBackToSignIn()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onSendResetLink() = Unit

    private fun onBackToSignIn() = sendSideEffect(sideEffect = SideEffect.NavigateToSignIn)
    /** ========================================================================================= */
}
