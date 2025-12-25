package com.example.tbc_collaboration_2025.data.repository

import com.example.tbc_collaboration_2025.data.remote.common.ResponseHandler
import com.example.tbc_collaboration_2025.data.remote.mapper.asResource
import com.example.tbc_collaboration_2025.data.remote.mapper.request.toData
import com.example.tbc_collaboration_2025.data.remote.mapper.response.toDomain
import com.example.tbc_collaboration_2025.data.remote.service.SignInService
import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.request.SignInRequest
import com.example.tbc_collaboration_2025.domain.model.response.SignInResponse
import com.example.tbc_collaboration_2025.domain.repository.SignInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@RemoteRepository
class SignInRepositoryImpl @Inject constructor(
    private val signInService: SignInService,
    private val responseHandler: ResponseHandler
) : SignInRepository {

    override fun signIn(request: SignInRequest): Flow<Resource<SignInResponse>> =
        responseHandler.safeApiCall { signInService.signIn(request = request.toData()) }
            .asResource { it.toDomain() }

}
