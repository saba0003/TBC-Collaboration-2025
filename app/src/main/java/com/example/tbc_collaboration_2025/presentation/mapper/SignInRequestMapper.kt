package com.example.tbc_collaboration_2025.presentation.mapper

import com.example.tbc_collaboration_2025.domain.model.request.SignInRequest as SignInRequestDomain
import com.example.tbc_collaboration_2025.presentation.screen.sign_in.SignInRequest as SignInRequestPresentation

fun SignInRequestPresentation.toDomain() = SignInRequestDomain(email = email, password = password)
