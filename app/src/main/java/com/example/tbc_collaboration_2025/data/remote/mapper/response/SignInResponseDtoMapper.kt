package com.example.tbc_collaboration_2025.data.remote.mapper.response

import com.example.tbc_collaboration_2025.data.remote.dto.response.SignInResponseDto
import com.example.tbc_collaboration_2025.domain.model.response.SignInResponse

fun SignInResponseDto.toDomain() = SignInResponse(
    token = token,
    userId = userId,
    fullName = fullName,
    role = role
)
