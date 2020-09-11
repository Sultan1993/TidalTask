package kz.maestrosultan.tidaltask.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kz.maestrosultan.tidaltask.core.DeezerJSON
import kz.maestrosultan.tidaltask.core.TestCoroutineRule
import kz.maestrosultan.tidaltask.core.network.SearchService
import kz.maestrosultan.tidaltask.data.entity.ApiArtist
import kz.maestrosultan.tidaltask.data.entity.ApiData
import kz.maestrosultan.tidaltask.data.repository.SearchRepositoryImpl
import kz.maestrosultan.tidaltask.domain.entity.Failed
import kz.maestrosultan.tidaltask.domain.entity.Success
import kz.maestrosultan.tidaltask.domain.repository.SearchRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var apiArtistList: List<ApiArtist>? = null

    @Mock
    private lateinit var searchService: SearchService
    private lateinit var searchRepository: SearchRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchRepository = SearchRepositoryImpl(searchService)

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, ApiArtist::class.java)
        val adapter: JsonAdapter<List<ApiArtist>> = moshi.adapter(type)
        apiArtistList = adapter.fromJson(DeezerJSON.searchJSON)
    }

    @Test
    fun `API artist list is not null`() {
        assertNotNull(apiArtistList)
    }

    @Test
    fun `repo returns success on correct response`() = testCoroutineRule.runBlockingTest {
        whenever(searchService.searchArtist("daft")).thenReturn(ApiData(apiArtistList!!))

        val result = searchRepository.searchArtist("daft")
        assertEquals(true, result is Success)
    }

    @Test
    fun `repo returns error on null response`() = testCoroutineRule.runBlockingTest {
        whenever(searchService.searchArtist("daft")).thenReturn(null)

        val result = searchRepository.searchArtist("daft")
        assertEquals(true, result is Failed)
    }

    @Test
    fun `repo returns correct list size`() = testCoroutineRule.runBlockingTest {
        whenever(searchService.searchArtist("daft")).thenReturn(ApiData(apiArtistList!!))

        val result = searchRepository.searchArtist("daft")

        if (result is Success) {
            val list = result.data
            assertEquals(3, list.size)
        } else {
            fail()
        }
    }
}