package kz.maestrosultan.tidaltask.core.network

import kz.maestrosultan.tidaltask.data.entity.ApiAlbum
import kz.maestrosultan.tidaltask.data.entity.ApiArtist
import kz.maestrosultan.tidaltask.data.entity.ApiData
import kz.maestrosultan.tidaltask.data.entity.ApiTrack
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("search/artist")
    suspend fun searchArtist(@Query("q") query: String): ApiData<List<ApiArtist>>
}

interface ArtistsService {

    @GET("artist/{artist_id}")
    suspend fun getArtist(@Path("artist_id") artistId: String): ApiArtist

    @GET("artist/{artist_id}/albums")
    suspend fun getArtistAlbums(@Path("artist_id") artistId: String): ApiData<List<ApiAlbum>>
}

interface AlbumsService {

    @GET("album/{album_id}")
    suspend fun getAlbumDetails(@Path("album_id") albumId: String): ApiAlbum

    @GET("album/{album_id}/tracks")
    suspend fun getAlbumTracks(@Path("album_id") albumId: String): ApiData<List<ApiTrack>>
}