package com.example.tbc_collaboration_2025.domain.use_case

import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.error.AppError
import com.example.tbc_collaboration_2025.domain.model.SignUpRequest
import com.example.tbc_collaboration_2025.domain.model.SignUpResponse
import com.example.tbc_collaboration_2025.domain.repository.SignUpRepository
import com.example.tbc_collaboration_2025.domain.util.SignUpValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    @param:RemoteRepository private val signUpRepository: SignUpRepository,
    private val validator: SignUpValidator
) {

    operator fun invoke(request: SignUpRequest): Flow<Resource<SignUpResponse>> =
        validator(request = request).let {
            if (it.isEmpty())
                signUpRepository.signUp(request = request)
            else
                flowOf(value = Resource.Error(errorCode = AppError.ValidationError(errors = it)))
        }

}
