package kz.maestrosultan.tidaltask.presentation.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.inflateBinding
import kz.maestrosultan.tidaltask.databinding.ItemArtistBinding
import kz.maestrosultan.tidaltask.domain.entity.Artist

class SearchAdapter(
    private val onArtistSelected: ((Artist) -> Unit)? = null
): ListAdapter<Artist, SearchAdapter.ArtistViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistViewHolder(parent.inflateBinding(R.layout.item_artist))

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ArtistViewHolder(
        private val binding: ItemArtistBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val artist = getItem(adapterPosition)
                onArtistSelected?.invoke(artist)
            }
        }

        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.name == newItem.name && oldItem.picture == newItem.picture
        }
    }
}