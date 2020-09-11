package kz.maestrosultan.tidaltask.data.repository

import kz.maestrosultan.tidaltask.core.network.CoroutineCaller
import kz.maestrosultan.tidaltask.core.network.SearchService
import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.repository.SearchRepository

/**
 *  For the simplicity, i've injected apiClient directly into repository
 *  If we've had local and remote storage, then i'd have created
 *  one more layer of abstraction (SearchRemoteDataSource and SearchLocalDataSource)
 */

class SearchRepositoryImpl(
    private val searchService: SearchService
) : SearchRepository, CoroutineCaller {

    override suspend fun searchArtist(query: String): Result<List<Artist>> =
        apiCall { searchService.searchArtist(query).data.map { it.map() }}
}