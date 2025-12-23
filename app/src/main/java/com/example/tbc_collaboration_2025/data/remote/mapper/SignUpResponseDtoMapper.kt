package com.example.tbc_collaboration_2025.data.remote.mapper

import com.example.tbc_collaboration_2025.data.remote.dto.response.SignUpResponseDto
import com.example.tbc_collaboration_2025.domain.model.SignUpResponse

fun SignUpResponseDto.toDomain() = SignUpResponse(
    id = id,
    eventId = eventId,
    userId = userId,
    status = status,
    registeredAt = registeredAt,
    position = position
)
