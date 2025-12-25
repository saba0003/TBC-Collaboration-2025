package com.example.tbc_collaboration_2025.data.remote.mapper.response

import com.example.tbc_collaboration_2025.data.remote.dto.response.SignUpResponseDto
import com.example.tbc_collaboration_2025.domain.model.response.SignUpResponse

fun SignUpResponseDto.toDomain() = SignUpResponse(
    token = token,
    userId = userId,
    fullName = fullName,
    role = role
)
