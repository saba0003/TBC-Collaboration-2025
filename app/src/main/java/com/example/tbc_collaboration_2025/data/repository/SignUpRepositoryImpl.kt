package com.example.tbc_collaboration_2025.data.repository

import com.example.tbc_collaboration_2025.data.remote.common.ResponseHandler
import com.example.tbc_collaboration_2025.data.remote.mapper.asResource
import com.example.tbc_collaboration_2025.data.remote.mapper.toData
import com.example.tbc_collaboration_2025.data.remote.mapper.toDomain
import com.example.tbc_collaboration_2025.data.remote.service.SignUpService
import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.SignUpRequest
import com.example.tbc_collaboration_2025.domain.model.SignUpResponse
import com.example.tbc_collaboration_2025.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@RemoteRepository
class SignUpRepositoryImpl @Inject constructor(
    private val signUpService: SignUpService,
    private val responseHandler: ResponseHandler
) : SignUpRepository {

    override fun signUp(request: SignUpRequest): Flow<Resource<SignUpResponse>> =
        responseHandler.safeApiCall { signUpService.signUp(request = request.toData()) }
            .asResource { it.toDomain() }

}
