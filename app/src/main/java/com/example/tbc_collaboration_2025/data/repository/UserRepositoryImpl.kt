package com.example.tbc_collaboration_2025.data.repository

import com.example.tbc_collaboration_2025.BuildConfig.BEARER
import com.example.tbc_collaboration_2025.data.remote.common.ResponseHandler
import com.example.tbc_collaboration_2025.data.remote.mapper.asResource
import com.example.tbc_collaboration_2025.data.remote.mapper.response.toDomain
import com.example.tbc_collaboration_2025.data.remote.service.FetchService
import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.response.UserProfileResponse
import com.example.tbc_collaboration_2025.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@RemoteRepository
class UserRepositoryImpl @Inject constructor(
    private val fetchService: FetchService,
    private val responseHandler: ResponseHandler
) : UserRepository {

    override fun getUser(token: String): Flow<Resource<UserProfileResponse>> =
        responseHandler.safeApiCall { fetchService.getUser(token = BEARER.plus(other = token)) }
            .asResource { it.toDomain() }

}
