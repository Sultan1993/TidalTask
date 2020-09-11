package kz.maestrosultan.tidaltask.data.entity

import com.squareup.moshi.JsonClass
import kz.maestrosultan.tidaltask.core.utils.Mappable
import kz.maestrosultan.tidaltask.domain.entity.Artist

@JsonClass(generateAdapter = true)
data class ApiArtist(
    val id: String,
    val name: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    val tracklist: String,
    val type: String,
    val link: String?,
    val nb_album: Int?,
    val nb_fan: Int?,
    val radio: Boolean?
): Mappable<Artist> {

    override fun map(): Artist {
        return Artist(
            id = id,
            name = name,
            picture = picture,
            pictureLarge = picture_big
        )
    }
}