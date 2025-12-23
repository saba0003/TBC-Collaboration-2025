package com.example.tbc_collaboration_2025.data.remote.mapper

import com.example.tbc_collaboration_2025.data.remote.dto.request.SignUpRequestDto
import com.example.tbc_collaboration_2025.domain.model.SignUpRequest

fun SignUpRequest.toData() = SignUpRequestDto(
    email = email,
    password = password,
    fullName = fullName
)
