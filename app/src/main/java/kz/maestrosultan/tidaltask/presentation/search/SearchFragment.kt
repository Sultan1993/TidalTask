package kz.maestrosultan.tidaltask.presentation.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kz.maestrosultan.tidaltask.R
import kz.maestrosultan.tidaltask.core.extensions.*
import kz.maestrosultan.tidaltask.databinding.FragmentSearchBinding
import kz.maestrosultan.tidaltask.domain.entity.Artist
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by dataBinding()
    private val searchViewModel: SearchViewModel by viewModel()

    private val searchAdapter = SearchAdapter(
        onArtistSelected = ::openArtistAlbums
    )

    private val searchQueryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            view?.hideKeyboard()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            searchViewModel.dispatch(SearchAction.Search(newText.toString()))
            return true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel = searchViewModel
            searchView.setOnQueryTextListener(searchQueryListener)

            //small delay before showing keyboard, to remove lag
            //with simultaneous fragment transition and keyboard appearance
            searchView.postDelayed({
                searchView.showKeyboard()
            }, 700)

            artistList.adapter = searchAdapter
            artistList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    searchView.hideKeyboard()
                }
            })
        }

        searchViewModel.uiState.observeEvent(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: SearchState) {
        when(state) {
            is SearchState.ShowingArtists ->
                searchAdapter.submitList(state.artists)

            is SearchState.SearchError -> {
                searchAdapter.submitList(emptyList())
                showSnackbar(R.string.error, duration = Snackbar.LENGTH_INDEFINITE, action = RetryAction {
                    val query = binding.searchView.query.toString()
                    searchViewModel.dispatch(SearchAction.Search(query))
                })
            }
        }
    }

    private fun openArtistAlbums(artist: Artist) {
        //we pass only artist ID. In case if we decide to have a deeplink to artist's albums
        //it will be easy to implement using the URL
        navigate(SearchFragmentDirections
            .actionSearchFragmentToArtistAlbumsFragment(artist.id))
    }
}