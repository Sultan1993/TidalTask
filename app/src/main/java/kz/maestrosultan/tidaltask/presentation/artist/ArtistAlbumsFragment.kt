package kz.maestrosultan.tidaltask.presentation.artist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.*
import kz.maestrosultan.tidaltask.databinding.FragmentArtistAlbumsBinding
import kz.maestrosultan.tidaltask.domain.entity.Album
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtistAlbumsFragment : Fragment(R.layout.fragment_artist_albums) {

    private val binding: FragmentArtistAlbumsBinding by dataBinding()
    private val args: ArtistAlbumsFragmentArgs by navArgs()
    private val albumsViewModel: ArtistAlbumsViewModel by viewModel { parametersOf(args.artistId) }
    private val albumsAdapter = ArtistAlbumsAdapter(
        onAlbumSelected = ::openAlbumDetails
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.hideKeyboard()

        with(binding) {
            viewModel = albumsViewModel
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            albumsList.adapter = albumsAdapter
        }

        albumsViewModel.uiState.observeEvent(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: ArtistAlbumsState) {
        when(state) {
            is ArtistAlbumsState.ShowingAlbums ->
                albumsAdapter.submitList(state.albums)

            is ArtistAlbumsState.AlbumsError ->
                showSnackbar(
                    text = state.error.message ?: "",
                    duration = Snackbar.LENGTH_INDEFINITE,
                    action = RetryAction { albumsViewModel.dispatch(ArtistAlbumsAction.FetchAlbums) }
                )
        }
    }

    private fun openAlbumDetails(album: Album) {
        //we pass only album ID. In case if we decide to have a deeplink to album
        //it will be easy to implement using the URL
        navigate(ArtistAlbumsFragmentDirections
            .actionArtistAlbumsFragmentToAlbumTracksFragment(album.id))
    }
}