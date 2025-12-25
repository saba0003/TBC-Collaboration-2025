package com.example.tbc_collaboration_2025.data.remote.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInResponseDto(
    val token: String,
    val userId: Int,
    val fullName: String,
    val role: String,
    val expiresAt: String
)
