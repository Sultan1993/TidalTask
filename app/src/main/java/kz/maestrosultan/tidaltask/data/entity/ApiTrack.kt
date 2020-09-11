package kz.maestrosultan.tidaltask.data.entity

import com.squareup.moshi.JsonClass
import kz.maestrosultan.tidaltask.core.utils.Mappable
import kz.maestrosultan.tidaltask.domain.entity.Track

@JsonClass(generateAdapter = true)
data class ApiTrack(
    val id: String,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String?,
    val isrc: String,
    val link: String,
    val duration: String,
    val track_position: Int,
    val disk_number: Int,
    val rank: String,
    val explicit_lyrics: Boolean,
    val artist: ApiTrackArtist
): Mappable<Track> {

    @JsonClass(generateAdapter = true)
    data class ApiTrackArtist(
        val id: String,
        val name: String,
        val tracklist: String,
        val type: String
    )

    override fun map(): Track {
        return Track(
            id = id,
            title = title,
            titleShort = title_short,
            position = track_position,
            diskNumber = disk_number,
            artistName = artist.name
        )
    }
}