package com.example.tbc_collaboration_2025.domain.model

data class SignUpResponse(
    val id: Int,
    val eventId: Int,
    val userId: Int,
    val status: String,
    val registeredAt: String,
    val position: String
)
