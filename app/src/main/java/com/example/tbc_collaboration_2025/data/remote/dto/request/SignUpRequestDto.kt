package com.example.tbc_collaboration_2025.data.remote.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequestDto(val email: String, val password: String, val fullName: String)
