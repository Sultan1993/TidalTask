package kz.maestrosultan.tidaltask.domain.usecase

import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.repository.ArtistsRepository

class GetArtistDetailsUseCase(
    private val repository: ArtistsRepository
) {

    suspend operator fun invoke(artistId: String): Result<Artist> =
        repository.getArtist(artistId)
}