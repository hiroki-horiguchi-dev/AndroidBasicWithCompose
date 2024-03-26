package com.example.compose.kotlinBasic.codelab.exercises

import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.musiccatalog.Song
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class SongTest {

    private lateinit var song: Song

    @Before
    fun setUp() {
        song = Song(
            title = "title",
            artist = "artist",
            releaseYear = 2024,
            numberOfTimesPlayed = 998
        )
    }

    @Test
    fun `再生回数が 999 以下の場合`() {
        assertEquals(false, song.isPopular())
    }

    @Test
    fun description() {
        assertEquals(
            "title, performed by artist, was released in 2024",
            song.description()
        )
    }
}

class SongTest2 {

    private lateinit var song: Song

    @Before
    fun setUp() {
        song = Song(
            title = "title",
            artist = "artist",
            releaseYear = 2024,
            numberOfTimesPlayed = 1000
        )
    }

    @Test
    fun `再生回数が 1000以上の場合`() {
        assertEquals(true, song.isPopular())
    }

    @Test
    fun description() {
        assertEquals(
            "title, performed by artist, was released in 2024",
            song.description()
        )
    }
}
