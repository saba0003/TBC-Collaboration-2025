package com.example.tbc_collaboration_2025.domain.repository

import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.request.SignUpRequest
import com.example.tbc_collaboration_2025.domain.model.response.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun signUp(request: SignUpRequest): Flow<Resource<SignUpResponse>>

}
