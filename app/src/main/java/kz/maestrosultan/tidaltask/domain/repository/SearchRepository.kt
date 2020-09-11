package kz.maestrosultan.tidaltask.domain.repository

import kz.maestrosultan.tidaltask.domain.entity.Artist
import kz.maestrosultan.tidaltask.domain.entity.Result

interface SearchRepository {
    suspend fun searchArtist(query: String): Result<List<Artist>>
}