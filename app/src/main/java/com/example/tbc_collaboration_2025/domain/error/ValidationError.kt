package com.example.tbc_collaboration_2025.domain.error

sealed class ValidationError {
    object FieldEmpty : ValidationError()
    object InvalidEmail : ValidationError()
    object PasswordTooWeak : ValidationError()
}
