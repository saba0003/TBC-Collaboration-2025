package com.example.tbc_collaboration_2025.domain.repository

import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.model.response.UserProfileResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<Resource<UserProfileResponse>>

}
