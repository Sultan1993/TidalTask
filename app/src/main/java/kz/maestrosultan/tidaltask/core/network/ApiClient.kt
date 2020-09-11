package kz.maestrosultan.tidaltask.core.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kz.maestrosultan.tidaltask.BuildConfig
import kz.maestrosultan.tidaltask.core.network.interceptor.JSONLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * TokenStorage can be injected here to handle OAuth token with AuthInterceptor
 */
class ApiClient {

    private val baseUrl: String
        get() = "https://api.deezer.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor(JSONLogger()).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .apply {
                    if (BuildConfig.DEBUG) { addInterceptor(loggingInterceptor) }
                }
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun <T> createService(kClass: Class<T>): T {
        return retrofit.create(kClass)
    }
}