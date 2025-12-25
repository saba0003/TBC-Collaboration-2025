package com.example.tbc_collaboration_2025.domain.model.response

data class UserProfileResponse(
    val id: Int,
    val fullName: String,
    val email: String,
    val role: String
)
