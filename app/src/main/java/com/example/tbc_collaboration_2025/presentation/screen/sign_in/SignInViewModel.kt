package com.example.tbc_collaboration_2025.presentation.screen.sign_in

import androidx.lifecycle.viewModelScope
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.DataStoreKeys.USER_TOKEN_PREF
import com.example.tbc_collaboration_2025.domain.use_case.SignInUseCase
import com.example.tbc_collaboration_2025.domain.use_case.data_store.SetPreferenceUseCase
import com.example.tbc_collaboration_2025.presentation.common.BaseViewModel
import com.example.tbc_collaboration_2025.presentation.mapper.toDomain
import com.example.tbc_collaboration_2025.presentation.screen.sign_in.SignInContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val setPreferenceUseCase: SetPreferenceUseCase
) : BaseViewModel<State, Event, SideEffect>(initialState = State(isLoading = true)) {


    override fun onEvent(event: Event): Unit = with(receiver = event) {
        when (this) {
            is Event.OnEmailChanged -> updateState { copy(email = value) }
            is Event.OnPasswordChanged -> updateState { copy(password = value) }
            is Event.OnRememberMeChanged -> updateState { copy(rememberMe = isChecked) }
            Event.OnSignInClicked -> onSignIn()
            Event.OnForgotPasswordClicked -> onForgotPassword()
            Event.OnSignUpClicked -> onSignUp()
        }
    }


    /** ======================================= HANDLERS ======================================== */
    private fun onSignIn() = with(receiver = state.value) {
        val request = SignInRequest(email = email, password = password).toDomain()

        viewModelScope.launch {
            signInUseCase(request = request).collect {
                when (it) {
                    is Resource.Success -> {
                        if (rememberMe)
                            setPreferenceUseCase(key = USER_TOKEN_PREF, value = it.data.token)
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

    private fun onForgotPassword() =
        sendSideEffect(sideEffect = SideEffect.NavigateToForgotPassword)

    private fun onSignUp() = sendSideEffect(sideEffect = SideEffect.NavigateToSignUp)
    /** ========================================================================================= */
}
