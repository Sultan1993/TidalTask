package kz.maestrosultan.tidaltask.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData<T>(val data: T)