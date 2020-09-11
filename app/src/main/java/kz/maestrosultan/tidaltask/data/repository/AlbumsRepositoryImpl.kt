package kz.maestrosultan.tidaltask.data.repository

import kz.maestrosultan.tidaltask.core.network.AlbumsService
import kz.maestrosultan.tidaltask.core.network.CoroutineCaller
import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Track
import kz.maestrosultan.tidaltask.domain.repository.AlbumsRepository

/**
 *  For the simplicity, i've injected apiClient directly into repository
 *  If we've had local and remote storage, then i'd have created
 *  one more layer of abstraction (AlbumsRemoteDataSource and AlbumsLocalDataSource)
 */

class AlbumsRepositoryImpl(
    private val albumsService: AlbumsService
): AlbumsRepository, CoroutineCaller {

    override suspend fun getAlbumDetails(albumId: String): Result<Album> =
        apiCall { albumsService.getAlbumDetails(albumId).map() }

    override suspend fun getAlbumTracks(albumId: String): Result<List<Track>> =
        apiCall { albumsService.getAlbumTracks(albumId).data.map { it.map() } }
}