@file:OptIn(ExperimentalTime::class)

package com.example.tbc_collaboration_2025.data.remote.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object InstantAdapter {
    @FromJson
    fun fromJson(value: String): Instant = Instant.parse(input = value)

    @ToJson
    fun toJson(value: Instant): String = value.toString()
}
