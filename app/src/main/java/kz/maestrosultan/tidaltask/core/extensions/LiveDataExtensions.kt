package kz.maestrosultan.tidaltask.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.maestrosultan.tidaltask.core.event.Event
import kz.maestrosultan.tidaltask.core.event.EventObserver

fun <T> LiveData<T>.debounce(
    duration: Long = 1000L,
    coroutineScope: CoroutineScope
) = MediatorLiveData<T>().also { mld ->

    val source = this
    var job: Job? = null

    mld.addSource(source) {
        job?.cancel()
        job = coroutineScope.launch {
            delay(duration)
            mld.value = source.value
        }
    }
}

fun <T> LiveData<Event<T>>.observeEvent(viewLifecycleOwner: LifecycleOwner, observer: ((T) -> Unit)) {
    observe(viewLifecycleOwner, EventObserver {
        observer(it)
    })
}