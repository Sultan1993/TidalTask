package kz.maestrosultan.tidaltask.domain.repository

import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result

interface ArtistsRepository {
    suspend fun getArtist(artistId: String): Result<Artist>
    suspend fun getArtistAlbums(artistId: String): Result<List<Album>>
}