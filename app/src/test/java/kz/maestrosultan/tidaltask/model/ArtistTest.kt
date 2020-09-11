package kz.maestrosultan.tidaltask.model

import com.squareup.moshi.Moshi
import kz.maestrosultan.tidaltask.core.DeezerJSON
import kz.maestrosultan.tidaltask.data.entity.ApiArtist
import kz.maestrosultan.tidaltask.domain.entity.Artist
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Provided only one model test for the simplicity
 */

class ArtistTest {

    private var apiArtist: ApiArtist?
    private var artist: Artist?

    init {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ApiArtist::class.java)
        apiArtist = adapter.fromJson(DeezerJSON.artistJSON)
        artist = apiArtist?.map()
    }

    @Test
    fun `JSON correctly parses`() {
        assertNotNull(apiArtist)
    }

    @Test
    fun `ApiArtist has correct id`() {
        assertEquals("27", apiArtist?.id)
    }

    @Test
    fun `ApiArtist has correct name`() {
        assertEquals("Daft Punk", apiArtist?.name)
    }

    @Test
    fun `ApiArtist maps to Artist correctly`() {
        assertNotNull(artist)
    }

    @Test
    fun `Artist id maps correctly`() {
        assertEquals(artist?.id, apiArtist?.id)
    }

    @Test
    fun `Artist name maps correctly`() {
        assertEquals(artist?.name, apiArtist?.name)
    }
}