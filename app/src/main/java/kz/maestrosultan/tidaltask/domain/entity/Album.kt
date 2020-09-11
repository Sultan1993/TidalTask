package kz.maestrosultan.tidaltask.domain.entity

data class Album(
    val id: String,
    val title: String,
    val cover: String,
    val coverLarge: String,
    var artist: Artist? = null,
    var tracks: List<TrackListItem>? = null
)