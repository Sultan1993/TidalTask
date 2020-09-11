package kz.maestrosultan.tidaltask.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kz.maestrosultan.tidaltask.core.TestCoroutineRule
import kz.maestrosultan.tidaltask.core.event.Event
import kz.maestrosultan.tidaltask.domain.entity.Album
import kz.maestrosultan.tidaltask.domain.repository.ArtistsRepository
import kz.maestrosultan.tidaltask.domain.usecase.GetArtistAlbumsUseCase
import kz.maestrosultan.tidaltask.presentation.artist.ArtistAlbumsState
import kz.maestrosultan.tidaltask.presentation.artist.ArtistAlbumsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ArtistAlbumsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var albumsViewModel: ArtistAlbumsViewModel

    @Mock
    private lateinit var artistsRepository: ArtistsRepository

    @Mock
    private lateinit var albumsUIObserver: Observer<Event<ArtistAlbumsState>>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    private val getArtistAlbums: GetArtistAlbumsUseCase by lazy { GetArtistAlbumsUseCase(artistsRepository) }
    private var albumList: List<Album>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        albumsViewModel = ArtistAlbumsViewModel("27", getArtistAlbums).apply {
            uiState.observeForever(albumsUIObserver)
            loading.observeForever(loadingObserver)
        }
    }

    @Test
    fun `changed to loading on init`() = testCoroutineRule.runBlockingTest() {
        verify(loadingObserver).onChanged(true)
    }
}