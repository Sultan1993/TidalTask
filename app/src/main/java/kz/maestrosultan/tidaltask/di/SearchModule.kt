package kz.maestrosultan.tidaltask.di

import kz.maestrosultan.tidaltask.data.repository.SearchRepositoryImpl
import kz.maestrosultan.tidaltask.domain.repository.SearchRepository
import kz.maestrosultan.tidaltask.domain.usecase.SearchArtistsUseCase
import kz.maestrosultan.tidaltask.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    single<SearchRepository> {
        SearchRepositoryImpl(searchService = get())
    }

    factory { SearchArtistsUseCase(repository = get()) }

    viewModel {
        SearchViewModel(
            searchArtists = get()
        )
    }
}