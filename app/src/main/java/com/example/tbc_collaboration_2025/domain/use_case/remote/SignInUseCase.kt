package com.example.tbc_collaboration_2025.domain.use_case

import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.common.Resource
import com.example.tbc_collaboration_2025.domain.error.AppError
import com.example.tbc_collaboration_2025.domain.model.request.SignInRequest
import com.example.tbc_collaboration_2025.domain.model.response.SignInResponse
import com.example.tbc_collaboration_2025.domain.repository.SignInRepository
import com.example.tbc_collaboration_2025.domain.util.SignInValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    @param:RemoteRepository private val signInRepository: SignInRepository,
    private val validator: SignInValidator
) {

    operator fun invoke(request: SignInRequest): Flow<Resource<SignInResponse>> =
        validator(request = request).let {
            if (it.isEmpty())
                signInRepository.signIn(request = request)
            else
                flowOf(value = Resource.Error(errorCode = AppError.ValidationError(errors = it)))
        }

}
