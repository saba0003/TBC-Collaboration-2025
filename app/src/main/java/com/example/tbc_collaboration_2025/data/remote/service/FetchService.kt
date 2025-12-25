package com.example.tbc_collaboration_2025.data.remote.service

import com.example.tbc_collaboration_2025.BuildConfig.AUTH
import com.example.tbc_collaboration_2025.BuildConfig.AUTHORIZATION
import com.example.tbc_collaboration_2025.BuildConfig.ME_ENDPOINT
import com.example.tbc_collaboration_2025.data.remote.dto.response.UserProfileResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FetchService {

    @GET(value = AUTH.plus(other = ME_ENDPOINT))
    suspend fun getUser(@Header(value = AUTHORIZATION) token: String): Response<UserProfileResponseDto>

}
