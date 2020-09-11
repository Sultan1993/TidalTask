package kz.maestrosultan.tidaltask.domain.entity

data class Track(
    val id: String,
    val title: String,
    val titleShort: String,
    val position: Int,
    val diskNumber: Int,
    val artistName: String
)

sealed class TrackListItem {
    class VolumeHeader(val number: Int): TrackListItem()
    class TrackItem(val track: Track): TrackListItem()
}