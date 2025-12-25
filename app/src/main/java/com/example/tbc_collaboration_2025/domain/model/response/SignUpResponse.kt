package com.example.tbc_collaboration_2025.domain.model.response

data class SignUpResponse(
    val token: String,
    val userId: Int,
    val fullName: String,
    val role: String
)
