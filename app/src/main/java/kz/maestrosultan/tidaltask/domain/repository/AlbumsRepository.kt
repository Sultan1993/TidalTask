package kz.maestrosultan.tidaltask.domain.repository

import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Track

interface AlbumsRepository {
    suspend fun getAlbumDetails(albumId: String): Result<Album>
    suspend fun getAlbumTracks(albumId: String): Result<List<Track>>
}