package com.example.tbc_collaboration_2025.domain.error

sealed class SignUpValidationError {
    object FieldEmpty : SignUpValidationError()
    object InvalidEmail : SignUpValidationError()
    object PasswordTooWeak : SignUpValidationError()
}
