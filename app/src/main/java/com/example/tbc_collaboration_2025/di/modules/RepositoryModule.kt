package com.example.tbc_collaboration_2025.di.modules

import com.example.tbc_collaboration_2025.data.repository.*
import com.example.tbc_collaboration_2025.di.qualifiers.RemoteRepository
import com.example.tbc_collaboration_2025.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    @RemoteRepository
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    @RemoteRepository
    fun bindSignUpRepository(impl: SignUpRepositoryImpl): SignUpRepository

    @Binds
    @Singleton
    @RemoteRepository
    fun bindSignInRepository(impl: SignInRepositoryImpl): SignInRepository

}
