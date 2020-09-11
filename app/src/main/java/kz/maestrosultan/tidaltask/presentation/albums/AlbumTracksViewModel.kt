package kz.maestrosultan.tidaltask.presentation.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.maestrosultan.tidaltask.core.event.Event
import kz.maestrosultan.tidaltask.core.lifecycle.SuspendableViewModel
import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.usecase.GetAlbumTracksUseCase

class AlbumTracksViewModel(
    private val albumId: String,
    private val getAlbumTracks: GetAlbumTracksUseCase
): SuspendableViewModel() {

    private val _uiState = MutableLiveData<Event<AlbumTracksState>>()
    val uiState: LiveData<Event<AlbumTracksState>> = _uiState

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        dispatch(AlbumTracksAction.FetchAlbumData)
    }

    fun dispatch(action: AlbumTracksAction) {
        when(action) {
            is AlbumTracksAction.FetchAlbumData -> fetchAlbumData()
        }
    }

    private fun fetchAlbumData() {
        viewModelScope.launch {
            _loading.value = true
            when(val result = getAlbumTracks(albumId)) {
                is Success -> emitState(AlbumTracksState.ShowingAlbum(result.data))
                is Failed -> emitState(AlbumTracksState.AlbumError(result.exception))
            }.also { _loading.value = false }
        }
    }

    private fun emitState(state: AlbumTracksState) {
        _uiState.value = Event(state)
    }
}

sealed class AlbumTracksState {
    class ShowingAlbum(val album: Album): AlbumTracksState()
    class AlbumError(val error: Throwable): AlbumTracksState()
}

sealed class AlbumTracksAction {
    object FetchAlbumData: AlbumTracksAction()
}