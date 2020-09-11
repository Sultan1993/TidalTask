package kz.maestrosultan.tidaltask.data.repository

import kz.maestrosultan.tidaltask.core.network.ArtistsService
import kz.maestrosultan.tidaltask.core.network.CoroutineCaller
import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.repository.ArtistsRepository

/**
 *  For the simplicity, i've injected apiClient directly into repository
 *  If we've had local and remote storage, then i'd have created
 *  one more layer of abstraction (ArtistsRemoteDataSource and ArtistsLocalDataSource)
 */

class ArtistsRepositoryImpl(
    private val artistsService: ArtistsService
): ArtistsRepository, CoroutineCaller {

    override suspend fun getArtist(artistId: String): Result<Artist> =
        apiCall { artistsService.getArtist(artistId).map() }

    override suspend fun getArtistAlbums(artistId: String): Result<List<Album>> =
        apiCall { artistsService.getArtistAlbums(artistId).data.map { it.map() } }
}