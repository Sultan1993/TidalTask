package kz.maestrosultan.tidaltask.data.entity

import com.squareup.moshi.JsonClass

class ApiException(val error: ApiError): Exception(error.message)

@JsonClass(generateAdapter = true)
class ApiError(
    val error: Error
) {

    @JsonClass(generateAdapter = true)
    class Error(
        val type: String,
        val message: String,
        val code: Int
    )

    val message: String
        get() = error.message
}

class TidalJSONException: Exception("Failed to process response") {

    override val message: String?
        get() = "Failed to process response"
}

class TidalConnectionException: Exception() {

    override val message: String?
        get() = "Failed to connect to server. Please, try again"
}

class TidalServerException: Exception() {

    override val message: String?
        get() = "Internal Server Error. Please, try again"
}