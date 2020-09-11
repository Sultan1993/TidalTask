package kz.maestrosultan.tidaltask.di

import kz.maestrosultan.tidaltask.data.repository.AlbumsRepositoryImpl
import kz.maestrosultan.tidaltask.domain.repository.AlbumsRepository
import kz.maestrosultan.tidaltask.domain.usecase.GetAlbumDetailsUseCase
import kz.maestrosultan.tidaltask.domain.usecase.GetAlbumTracksUseCase
import kz.maestrosultan.tidaltask.presentation.albums.AlbumTracksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val albumsModule = module {

    single<AlbumsRepository> {
        AlbumsRepositoryImpl(albumsService = get())
    }

    factory { GetAlbumDetailsUseCase(repository = get()) }

    factory { GetAlbumTracksUseCase(repository = get()) }

    viewModel { (albumId: String) ->
        AlbumTracksViewModel(
            albumId = albumId,
            getAlbumTracks = get()
        )
    }
}