package com.example.tbc_collaboration_2025.data.remote.dto.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserProfileResponseDto(
    val id: Int,
    val fullName: String,
    val email: String,
    val role: String
)
