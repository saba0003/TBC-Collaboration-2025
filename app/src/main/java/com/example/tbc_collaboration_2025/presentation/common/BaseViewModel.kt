package com.example.tbc_collaboration_2025.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EVENT, SIDE_EFFECT>(initialState: STATE) : ViewModel() {

    private val _state = MutableStateFlow(value = initialState)
    val state = _state.asStateFlow()

    private val _sideEffect = Channel<SIDE_EFFECT>(capacity = Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()


    open fun onEvent(event: EVENT) = Unit

    protected fun updateState(reducer: STATE.() -> STATE) = _state.update { it.reducer() }

    protected fun sendSideEffect(sideEffect: SIDE_EFFECT) {
        viewModelScope.launch { _sideEffect.send(element = sideEffect) }
    }

    protected suspend fun emitSideEffect(sideEffect: SIDE_EFFECT) =
        _sideEffect.send(element = sideEffect)

}
