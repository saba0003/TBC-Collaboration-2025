package com.example.tbc_collaboration_2025.presentation.mapper

import com.example.tbc_collaboration_2025.domain.model.request.SignUpRequest as SignUpRequestDomain
import com.example.tbc_collaboration_2025.presentation.screen.sign_up.SignUpRequest as SignUpRequestPresentation

fun SignUpRequestPresentation.toDomain() = SignUpRequestDomain(
    fullName = fullName,
    email = email,
    password = password
)
