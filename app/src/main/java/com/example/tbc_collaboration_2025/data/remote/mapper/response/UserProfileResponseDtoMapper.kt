package com.example.tbc_collaboration_2025.data.remote.mapper.response

import com.example.tbc_collaboration_2025.data.remote.dto.response.UserProfileResponseDto
import com.example.tbc_collaboration_2025.domain.model.response.UserProfileResponse

fun UserProfileResponseDto.toDomain() = UserProfileResponse(
    id = id,
    fullName = fullName,
    email = email,
    role = role
)
