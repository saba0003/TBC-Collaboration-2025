package com.example.tbc_collaboration_2025.domain.use_case.remote

import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.response.UserProfileResponse
import com.example.tbc_collaboration_2025.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(@param:RemoteRepository private val userRepository: UserRepository) {

    operator fun invoke(): Flow<Resource<UserProfileResponse>> = userRepository.getUser()

}
