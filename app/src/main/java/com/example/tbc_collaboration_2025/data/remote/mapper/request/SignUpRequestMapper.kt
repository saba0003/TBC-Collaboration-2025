package com.example.tbc_collaboration_2025.data.remote.mapper.request

import com.example.tbc_collaboration_2025.data.remote.dto.request.SignUpRequestDto
import com.example.tbc_collaboration_2025.domain.model.request.SignUpRequest

fun SignUpRequest.toData() =
    SignUpRequestDto(email = email, password = password, fullName = fullName)
