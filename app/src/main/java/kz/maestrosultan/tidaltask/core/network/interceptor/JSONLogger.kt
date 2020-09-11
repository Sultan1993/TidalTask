package kz.maestrosultan.tidaltask.core.network.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject

class JSONLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val json = JSONObject(message).toString(4)
                val jsonList = json.chunked(500)
                jsonList.forEach { Log.d("API", it) }
            } catch (e: JSONException) {
                Log.d("API_ERROR", e.toString())
            }
        } else {
            Log.d("API", message)
        }
    }
}