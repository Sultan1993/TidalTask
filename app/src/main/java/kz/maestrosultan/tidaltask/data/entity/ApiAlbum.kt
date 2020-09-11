package kz.maestrosultan.tidaltask.data.entity

import com.squareup.moshi.JsonClass
import kz.maestrosultan.tidaltask.core.utils.Mappable
import kz.maestrosultan.tidaltask.domain.entity.Album

@JsonClass(generateAdapter = true)
data class ApiAlbum(
    val id: String,
    val title: String,
    val link: String,
    val cover: String,
    val cover_small: String,
    val cover_medium: String,
    val cover_big: String,
    val cover_xl: String,
    val genre_id: Int,
    val fans: Int,
    val release_date: String,
    val record_type: String,
    val tracklist: String,
    val explicit_lyrics: Boolean,
    val type: String,
    val artist: ApiArtist?
): Mappable<Album> {

    override fun map(): Album {
        return Album(
            id = id,
            title = title,
            cover = cover_big,
            coverLarge = cover_xl,
            artist = artist?.map()
        )
    }
}