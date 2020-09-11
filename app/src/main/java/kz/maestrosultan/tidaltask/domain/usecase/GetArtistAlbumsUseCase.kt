package kz.maestrosultan.tidaltask.domain.usecase

import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.repository.ArtistsRepository

/**
 * I create two requests because design wants me to have artist's name below
 * album name. Album endpoint doesn't provide such information, so i fetch it
 * from artist details
 */

class GetArtistAlbumsUseCase(
    private val repository: ArtistsRepository
) {

    suspend operator fun invoke(artistId: String): Result<List<Album>> {
        val artistResult = repository.getArtist(artistId)
        val albumsResult = repository.getArtistAlbums(artistId)

        return if (artistResult is Success && albumsResult is Success) {
            albumsResult.data.forEach { it.artist = artistResult.data }
            albumsResult
        } else {
            if (artistResult is Failed) {
                artistResult
            } else {
                albumsResult
            }
        }
    }
}