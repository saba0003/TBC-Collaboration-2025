package com.example.tbc_collaboration_2025.di.modules

import com.example.tbc_collaboration_2025.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideFetchService(retrofit: Retrofit): FetchService =
        retrofit.create(FetchService::class.java)

    @Provides
    @Singleton
    fun provideSignUpService(retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Provides
    @Singleton
    fun provideSignInService(retrofit: Retrofit): SignInService =
        retrofit.create(SignInService::class.java)

}
