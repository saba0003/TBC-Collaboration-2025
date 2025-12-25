package com.example.tbc_collaboration_2025.data.local

import com.example.tbc_collaboration_2025.BuildConfig.AUTHORIZATION
import com.example.tbc_collaboration_2025.BuildConfig.BEARER
import com.example.tbc_collaboration_2025.domain.model.DataStoreKeys.TOKEN_KEY
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GetUserInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Use runBlocking to get the value from the Flow synchronously
        val token = runBlocking {
            dataStoreManager.getPreference(key = TOKEN_KEY, defaultValue = "").firstOrNull()
        }

        if (!token.isNullOrBlank())
            requestBuilder.addHeader(name = AUTHORIZATION, value = BEARER.plus(other = token))

        return chain.proceed(request = requestBuilder.build())
    }
}