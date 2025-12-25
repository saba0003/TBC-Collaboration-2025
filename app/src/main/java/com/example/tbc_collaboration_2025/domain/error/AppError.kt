package com.example.tbc_collaboration_2025.domain.error

sealed class AppError {
    data class Message(val value: String) : AppError()
    data object NetworkError : AppError()
    data object ApiError : AppError()
    data object StateError : AppError()
    data class ValidationError(val errors: List<com.example.tbc_collaboration_2025.domain.error.ValidationError>) : AppError()
    data object UnknownError : AppError()
}
