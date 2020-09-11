package kz.maestrosultan.tidaltask.domain.entity

@Suppress("unused")
sealed class Result<out T: Any?> {
    fun value(): T? {
        return if (this is Success) this.data else null
    }
}

data class Success<out T : Any?>(val data: T) : Result<T>()
data class Failed(val exception: Throwable) : Result<Nothing>()

fun <T: Any> Result<T>.ifSuccess(action: (T) -> Unit) : Result<T> {
    return when (this) {
        is Success -> {
            action(this.data)
            this
        }
        is Failed -> {
            this
        }
    }
}

suspend fun <T: Any> Result<T>.ifSuccessSuspended(action: suspend (T) -> Unit) : Result<T> {
    return when (this) {
        is Success -> {
            action(this.data)
            this
        }
        is Failed -> {
            this
        }
    }
}

fun <T: Any> Result<T>.ifFailed(action: () -> Unit) : Result<T> {
    return when (this) {
        is Success -> {
            this
        }
        is Failed -> {
            action()
            this
        }
    }
}

suspend fun <T: Any> Result<T>.ifFailedSuspended(action: suspend () -> Unit) : Result<T> {
    return when (this) {
        is Success -> {
            this
        }
        is Failed -> {
            action()
            this
        }
    }
}