package com.example.tbc_collaboration_2025.data.remote.mapper.request

import com.example.tbc_collaboration_2025.data.remote.dto.request.SignInRequestDto
import com.example.tbc_collaboration_2025.domain.model.request.SignInRequest

fun SignInRequest.toData() = SignInRequestDto(email = email, password = password)
