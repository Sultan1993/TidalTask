package kz.maestrosultan.tidaltask.domain.usecase

import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.repository.AlbumsRepository



class GetAlbumDetailsUseCase(
    private val repository: AlbumsRepository
) {

    suspend operator fun invoke(albumId: String): Result<Album> =
        repository.getAlbumDetails(albumId)
}