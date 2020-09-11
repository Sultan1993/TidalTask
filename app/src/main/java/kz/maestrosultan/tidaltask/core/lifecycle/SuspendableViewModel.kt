package kz.maestrosultan.tidaltask.core.lifecycle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class SuspendableViewModel(
    private val coroutinesContextProvider: CoroutinesContextProvider = CoroutinesContextProvider()
): ViewModel(), CoroutineScope {

    val main: CoroutineContext = coroutinesContextProvider.main
    val io: CoroutineContext = coroutinesContextProvider.io

    @Suppress("MemberVisibilityCanBePrivate")
    protected var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + main

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}