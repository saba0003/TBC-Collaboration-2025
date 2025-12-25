package com.example.tbc_collaboration_2025.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.tbc_collaboration_2025.domain.model.DataStoreKeys.TOKEN_KEY
import com.example.tbc_collaboration_2025.domain.use_case.data_store.GetPreferenceUseCase
import com.example.tbc_collaboration_2025.presentation.common.BaseViewModel
import com.example.tbc_collaboration_2025.presentation.screen.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getPreferenceUseCase: GetPreferenceUseCase) :
    BaseViewModel<State, Unit, SideEffect>(initialState = State(isLoading = true)) {

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val token = getPreferenceUseCase(key = TOKEN_KEY, defaultValue = "").first()
            if (token.isNotEmpty()) {
                emitSideEffect(SideEffect.NavigateToEventHub)
            } else {
                emitSideEffect(SideEffect.NavigateToSignUp)
            }
            updateState { copy(isLoading = false) }
        }
    }
}
