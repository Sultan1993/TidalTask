package kz.maestrosultan.tidaltask.core.lifecycle

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

data class CoroutinesContextProvider(
    val main: CoroutineContext = Dispatchers.Main,
    val io: CoroutineContext = Dispatchers.IO
)
