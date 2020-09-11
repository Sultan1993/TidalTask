package kz.maestrosultan.tidaltask.core.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kz.maestrosultan.tidaltask.data.entity.*
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Success
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface CoroutineCaller {

    suspend fun <T> apiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        result: suspend () -> T
    ): Result<T> {
        return try {
            coroutineScope { Success(result.invoke()) }
        } catch (e: Throwable) {
            handleException(e)
        }
    }

    private fun <T> handleException(e: Throwable): Result<T> = when (e) {
        is ConnectException, is UnknownHostException, is SocketTimeoutException -> {
            Failed(TidalConnectionException())
        }

        is JsonDataException -> {
            Failed(TidalJSONException())
        }

        is HttpException -> when (e.code()) {
            HttpURLConnection.HTTP_NOT_FOUND, HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                Failed(TidalServerException())
            }
            else -> {
                try {
                    val moshi = Moshi.Builder().build()
                    val adapter = moshi.adapter(ApiError::class.java)
                    val apiException = adapter.fromJson(e.response()?.errorBody()?.source())
                    Failed(ApiException(apiException!!))
                } catch (e: Exception) {
                    Failed(TidalJSONException())
                }
            }
        }
        else -> {
            Failed(e)
        }
    }
}