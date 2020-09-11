package kz.maestrosultan.tidaltask.domain.usecase

import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.entity.Result
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.entity.TrackListItem
import kz.maestrosultan.tidaltask.domain.repository.AlbumsRepository

/**
 * I create two requests because design wants me to have artist's name below
 * album name. Album tracks endpoint doesn't provide such information, so i fetch it
 * from Album details endpoint
 */

class GetAlbumTracksUseCase(
    private val repository: AlbumsRepository
) {

    suspend operator fun invoke(albumId: String): Result<Album> {
        val albumDetailsResult = repository.getAlbumDetails(albumId)
        val albumTracksResult = repository.getAlbumTracks(albumId)

        return if (albumDetailsResult is Success && albumTracksResult is Success) {
            val tracks = albumTracksResult.data
            val volumes = tracks
                .map { it.diskNumber }
                .distinct()
                .sorted()

            val trackItems = if (volumes.count() > 1) {
                mutableListOf<TrackListItem>().apply {
                    volumes.forEach { volume ->
                        add(TrackListItem.VolumeHeader(volume))

                        val volumeTracks = tracks
                            .filter { it.diskNumber == volume }
                            .map { TrackListItem.TrackItem(it) }

                        addAll(volumeTracks)
                    }
                }
            } else {
                tracks.map { TrackListItem.TrackItem(it) }
            }

            albumDetailsResult.data.tracks = trackItems
            albumDetailsResult
        } else {
            albumDetailsResult
        }
    }

}