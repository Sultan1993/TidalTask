package kz.maestrosultan.tidaltask.presentation.artist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.inflateBinding
import kz.maestrosultan.tidaltask.databinding.ItemAlbumBinding
import kz.maestrosultan.tidaltask.domain.entity.Album

class ArtistAlbumsAdapter(
    private val onAlbumSelected: ((Album) -> Unit)? = null
): ListAdapter<Album, ArtistAlbumsAdapter.AlbumViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumViewHolder(parent.inflateBinding(R.layout.item_album))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class AlbumViewHolder(
        private val binding: ItemAlbumBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.albumCoverCard.setOnClickListener {
                val album = getItem(adapterPosition)
                onAlbumSelected?.invoke(album)
            }
        }

        fun bind(album: Album) {
            binding.album = album
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.artist?.id == newItem.artist?.id &&
                    oldItem.cover == newItem.cover
        }
    }
}