package com.example.tbc_collaboration_2025.data.remote.service

import com.example.tbc_collaboration_2025.BuildConfig.AUTH
import com.example.tbc_collaboration_2025.BuildConfig.REGISTER_ENDPOINT
import com.example.tbc_collaboration_2025.data.remote.dto.request.SignUpRequestDto
import com.example.tbc_collaboration_2025.data.remote.dto.response.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.POST

interface SignUpService {

    @POST(value = AUTH.plus(other = REGISTER_ENDPOINT))
    suspend fun signUp(request: SignUpRequestDto): Response<SignUpResponseDto>

}
