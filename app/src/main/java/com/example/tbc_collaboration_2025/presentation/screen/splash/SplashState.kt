package com.example.tbc_collaboration_2025.presentation.screen.splash

sealed interface SplashState {
    data object Loading : SplashState
    data object Finished : SplashState
}
