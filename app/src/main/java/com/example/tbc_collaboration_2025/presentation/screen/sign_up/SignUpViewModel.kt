package com.example.tbc_collaboration_2025.presentation.screen.sign_up

import androidx.lifecycle.viewModelScope
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.use_case.SignUpUseCase
import com.example.tbc_collaboration_2025.presentation.common.BaseViewModel
import com.example.tbc_collaboration_2025.presentation.mapper.toDomain
import com.example.tbc_collaboration_2025.presentation.screen.sign_up.SignUpContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :
    BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    override fun onEvent(event: Event): Unit = with(receiver = event) {
        when (this) {
            is Event.OnFirstNameChanged -> updateState { copy(firstName = firstName) }
            is Event.OnLastNameChanged -> updateState { copy(lastName = lastName) }
            is Event.OnEmailChanged -> updateState { copy(email = email) }
            is Event.OnPasswordChanged -> updateState { copy(password = password) }
            is Event.OnConfirmPasswordChanged -> updateState { copy(confirmPassword = confirmPassword) }
            is Event.OnAgreementChanged -> updateState { copy(isAgreed = isChecked) }
            Event.OnSignUpClicked -> onSignUp()
            Event.OnSignInClicked -> onSignIn()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onSignUp() = with(receiver = state.value) {
        val request = SignUpRequest(
            email = email,
            password = password,
            fullName = "$firstName $lastName"
        ).toDomain()

        viewModelScope.launch {
            signUpUseCase(request = request).collect {
                when (it) {
                    is Resource.Success -> {
                        updateState { copy(isLoading = false) }
                        emitSideEffect(sideEffect = SideEffect.NavigateToEventHub)
                    }

                    is Resource.Error -> {
                        updateState { copy(isLoading = false, errorCode = it.errorCode) }
                        emitSideEffect(sideEffect = SideEffect.ShowError(errorCode = it.errorCode))
                    }

                    is Resource.Loader -> updateState { copy(isLoading = it.isLoading) }
                }
            }
        }
    }

    private fun onSignIn() = sendSideEffect(sideEffect = SideEffect.NavigateToSignIn)
    /** ========================================================================================= */
}
