package kz.maestrosultan.tidaltask.di

import kz.maestrosultan.tidaltask.data.repository.ArtistsRepositoryImpl
import kz.maestrosultan.tidaltask.domain.repository.ArtistsRepository
import kz.maestrosultan.tidaltask.domain.usecase.GetArtistAlbumsUseCase
import kz.maestrosultan.tidaltask.domain.usecase.GetArtistDetailsUseCase
import kz.maestrosultan.tidaltask.presentation.artist.ArtistAlbumsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val artistsModule = module {

    single<ArtistsRepository> {
        ArtistsRepositoryImpl(artistsService = get())
    }

    factory { GetArtistDetailsUseCase(repository = get()) }

    factory { GetArtistAlbumsUseCase(repository = get()) }

    viewModel { (artistId: String) ->
        ArtistAlbumsViewModel(
            artistId = artistId,
            getArtistAlbums = get()
        )
    }
}