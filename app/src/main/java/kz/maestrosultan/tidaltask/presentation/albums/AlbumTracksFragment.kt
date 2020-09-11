package kz.maestrosultan.tidaltask.presentation.albums

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.RetryAction
import kz.maestrosultan.tidaltask.core.extensions.dataBinding
import kz.maestrosultan.tidaltask.core.extensions.observeEvent
import kz.maestrosultan.tidaltask.core.extensions.showSnackbar
import kz.maestrosultan.tidaltask.databinding.FragmentAlbumTracksBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * According to design there should be artist's name below album title
 * But API doesn't provide such information. And at this point i couldn't get
 * artist's ID anywhere. I can't pass it from previous screen, because it would create
 * difficulties if we would want to implement deeplinks
 */

class AlbumTracksFragment : Fragment(R.layout.fragment_album_tracks) {

    private val binding: FragmentAlbumTracksBinding by dataBinding()
    private val args: AlbumTracksFragmentArgs by navArgs()
    private val albumViewModel: AlbumTracksViewModel by viewModel { parametersOf(args.albumId) }
    private val tracksAdapter = AlbumTracksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel = albumViewModel
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            trackList.adapter = tracksAdapter
        }

        albumViewModel.uiState.observeEvent(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: AlbumTracksState) {
        when(state) {
            is AlbumTracksState.ShowingAlbum -> {
                binding.collapsingToolbar.title = state.album.title
                binding.albumHeaderView.configure(state.album)
                tracksAdapter.submitList(state.album.tracks)
            }

            is AlbumTracksState.AlbumError ->
                showSnackbar(
                    text = state.error.message ?: "",
                    duration = Snackbar.LENGTH_INDEFINITE,
                    action = RetryAction { albumViewModel.dispatch(AlbumTracksAction.FetchAlbumData) }
                )
        }
    }
}