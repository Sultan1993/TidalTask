package kz.maestrosultan.tidaltask.di

import kz.maestrosultan.tidaltask.core.network.AlbumsService
import kz.maestrosultan.tidaltask.core.network.ApiClient
import kz.maestrosultan.tidaltask.core.network.ArtistsService
import kz.maestrosultan.tidaltask.core.network.SearchService
import org.koin.dsl.module

val apiClientModule = module {
    val client = ApiClient()

    single { client }
    factory { client.createService(SearchService::class.java) }
    factory { client.createService(ArtistsService::class.java) }
    factory { client.createService(AlbumsService::class.java) }
}

//I could have loaded modules onAttach and unload them onDetach of corresponding fragments,
//but due to simplicity of the app I decided to load them at startup
val appModule = listOf(
    apiClientModule,
    searchModule,
    artistsModule,
    albumsModule
)