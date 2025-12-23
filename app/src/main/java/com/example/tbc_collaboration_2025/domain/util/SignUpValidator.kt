package com.example.tbc_collaboration_2025.domain.util

import com.example.tbc_collaboration_2025.domain.error.SignUpValidationError
import com.example.tbc_collaboration_2025.domain.model.SignUpRequest
import javax.inject.Inject

class SignUpValidator @Inject constructor() {

    companion object {
        private const val EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        private const val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"
    }


    operator fun invoke(request: SignUpRequest): List<SignUpValidationError> =
        with(receiver = request) {
            buildList {
                if (fullName.isBlank())
                    add(element = SignUpValidationError.FieldEmpty)
                if (EMAIL_PATTERN.toRegex().matches(input = email).not())
                    add(element = SignUpValidationError.InvalidEmail)
                if (PASSWORD_PATTERN.toRegex().matches(input = password).not())
                    add(element = SignUpValidationError.PasswordTooWeak)
            }
        }

}
