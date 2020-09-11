package kz.maestrosultan.tidaltask.presentation.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.maestrosultan.tidaltask.core.event.Event
import kz.maestrosultan.tidaltask.core.lifecycle.SuspendableViewModel
import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.usecase.GetArtistAlbumsUseCase

class ArtistAlbumsViewModel(
    private val artistId: String,
    private val getArtistAlbums: GetArtistAlbumsUseCase
): SuspendableViewModel() {

    private val _uiState = MutableLiveData<Event<ArtistAlbumsState>>()
    val uiState: LiveData<Event<ArtistAlbumsState>> = _uiState

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        dispatch(ArtistAlbumsAction.FetchAlbums)
    }

    fun dispatch(action: ArtistAlbumsAction) {
        when(action) {
            is ArtistAlbumsAction.FetchAlbums -> fetchAlbums()
        }
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            _loading.value = true
            when(val result = getArtistAlbums(artistId)) {
                is Success -> emitState(ArtistAlbumsState.ShowingAlbums(result.data))
                is Failed -> emitState(ArtistAlbumsState.AlbumsError(result.exception))
            }.also { _loading.value = false }
        }
    }

    private fun emitState(state: ArtistAlbumsState) {
        _uiState.value = Event(state)
    }
}

sealed class ArtistAlbumsState {
    class ShowingAlbums(val albums: List<Album>): ArtistAlbumsState()
    class AlbumsError(val error: Throwable): ArtistAlbumsState()
}

sealed class ArtistAlbumsAction {
    object FetchAlbums: ArtistAlbumsAction()
}