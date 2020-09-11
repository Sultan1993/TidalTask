package kz.maestrosultan.tidaltask.domain.usecase

import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.repository.SearchRepository

class SearchArtistsUseCase(
    private val repository: SearchRepository
) {

    //we return empty list if query is too short
    suspend operator fun invoke(query: String): Result<List<Artist>> {
        if (query.length < 2) {
            return Success(emptyList())
        }
        return repository.searchArtist(query)
    }
}