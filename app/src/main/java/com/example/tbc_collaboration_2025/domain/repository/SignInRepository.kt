package com.example.tbc_collaboration_2025.domain.repository

import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.request.SignInRequest
import com.example.tbc_collaboration_2025.domain.model.response.SignInResponse
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    fun signIn(request: SignInRequest): Flow<Resource<SignInResponse>>

}
