package com.example.tbc_collaboration_2025.data.remote.service

import com.example.tbc_collaboration_2025.BuildConfig.AUTH
import com.example.tbc_collaboration_2025.BuildConfig.LOGIN_ENDPOINT
import com.example.tbc_collaboration_2025.data.remote.dto.request.SignInRequestDto
import com.example.tbc_collaboration_2025.data.remote.dto.response.SignInResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST(value = AUTH.plus(other = LOGIN_ENDPOINT))
    suspend fun signIn(@Body request: SignInRequestDto): Response<SignInResponseDto>

}
