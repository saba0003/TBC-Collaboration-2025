package com.example.tbc_collaboration_2025.domain.util

import com.example.tbc_collaboration_2025.domain.error.ValidationError
import com.example.tbc_collaboration_2025.domain.model.request.SignUpRequest
import javax.inject.Inject

class SignUpValidator @Inject constructor() {

    operator fun invoke(request: SignUpRequest): List<ValidationError> =
        with(receiver = request) {
            buildList {
                if (fullName.isBlank().or(other = email.isBlank()).or(other = password.isBlank()))
                    add(element = ValidationError.FieldEmpty)
                if (EMAIL_PATTERN.toRegex().matches(input = email).not())
                    add(element = ValidationError.InvalidEmail)
                if (PASSWORD_PATTERN.toRegex().matches(input = password).not())
                    add(element = ValidationError.PasswordTooWeak)
            }
        }

    private companion object {
        const val EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        const val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"
    }
}
