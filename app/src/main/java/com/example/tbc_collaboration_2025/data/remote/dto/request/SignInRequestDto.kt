package com.example.tbc_collaboration_2025.data.remote.dto.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequestDto(val email: String, val password: String)
