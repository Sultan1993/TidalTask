package kz.maestrosultan.tidaltask.presentation.albums

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.inflateBinding
import kz.maestrosultan.tidaltask.databinding.ItemTrackBinding
import kz.maestrosultan.tidaltask.databinding.ItemVolumeHeaderBinding
import kz.maestrosultan.tidaltask.domain.entity.TrackListItem

class AlbumTracksAdapter: ListAdapter<TrackListItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int) = when(getItem(position)) {
        is TrackListItem.VolumeHeader -> R.layout.item_volume_header
        is TrackListItem.TrackItem -> R.layout.item_track
        else -> throw IllegalArgumentException("View type not supported")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        R.layout.item_volume_header -> HeaderViewHolder(parent.inflateBinding(viewType))
        R.layout.item_track -> TrackViewHolder(parent.inflateBinding(viewType))
        else -> throw IllegalArgumentException("ViewHolder cannot be created from $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(getItemViewType(position)) {
        R.layout.item_volume_header -> {
            val item = getItem(position) as TrackListItem.VolumeHeader
            (holder as HeaderViewHolder).bind(item)
        }

        R.layout.item_track -> {
            val item = getItem(position) as TrackListItem.TrackItem
            (holder as TrackViewHolder).bind(item)
        }

        else -> Unit
    }

    class HeaderViewHolder(
        private val binding: ItemVolumeHeaderBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(header: TrackListItem.VolumeHeader) {
            binding.volume = header
        }
    }

    class TrackViewHolder(
        private val binding: ItemTrackBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(track: TrackListItem.TrackItem) {
            binding.track = track
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<TrackListItem>() {
        override fun areItemsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
            if (oldItem is TrackListItem.VolumeHeader && newItem is TrackListItem.VolumeHeader) {
                return oldItem.number == newItem.number
            }

            if (oldItem is TrackListItem.TrackItem && newItem is TrackListItem.TrackItem) {
                return oldItem.track.id == newItem.track.id
            }

            return false
        }

        override fun areContentsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
            if (oldItem is TrackListItem.VolumeHeader && newItem is TrackListItem.VolumeHeader) {
                return oldItem.number == newItem.number
            }

            if (oldItem is TrackListItem.TrackItem && newItem is TrackListItem.TrackItem) {
                return oldItem.track.title == newItem.track.title &&
                        oldItem.track.artistName == newItem.track.artistName &&
                        oldItem.track.position == newItem.track.position &&
                        oldItem.track.diskNumber == newItem.track.diskNumber
            }

            return false
        }
    }
}