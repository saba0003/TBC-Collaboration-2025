package com.example.tbc_collaboration_2025.data.remote.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponseDto(
    val id: Int,
    val eventId: Int,
    val userId: Int,
    val status: String,
    val registeredAt: String,
    val position: String
)
