package kz.maestrosultan.tidaltask.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.maestrosultan.tidaltask.core.event.Event
import kz.maestrosultan.tidaltask.core.extensions.debounce
import kz.maestrosultan.tidaltask.core.lifecycle.SuspendableViewModel
import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.usecase.SearchArtistsUseCase

class SearchViewModel(
    private val searchArtists: SearchArtistsUseCase
) : SuspendableViewModel() {

    companion object {
        private const val DELAY = 500L
    }

    private val _uiState = MediatorLiveData<Event<SearchState>>()
    val uiState: LiveData<Event<SearchState>> = _uiState

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val query = MutableLiveData<String>()

    init {
        _loading.value = false
        _uiState.addSource(query.debounce(DELAY, this), ::search)
    }

    fun dispatch(action: SearchAction) {
        when(action) {
            is SearchAction.Search -> {
                _loading.value = false
                query.value = action.query
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch {
            _loading.value = true

            when(val result = searchArtists(query)) {
                is Success -> emitState(SearchState.ShowingArtists(result.data))
                is Failed -> emitState(SearchState.SearchError(result.exception))
            }.also { _loading.value = false }
        }
    }

    private fun emitState(state: SearchState) {
        _uiState.value = Event(state)
    }
}

sealed class SearchState {
    class ShowingArtists(val artists: List<Artist>): SearchState()
    class SearchError(val error: Throwable): SearchState()
}

sealed class SearchAction {
    class Search(val query: String): SearchAction()
}