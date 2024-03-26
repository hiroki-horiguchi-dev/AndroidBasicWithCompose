package com.example.compose.kotlinBasic.codelab.exercises

import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.movie.ticketPrice
import junit.framework.TestCase.assertEquals
import org.junit.Test


class MovieKtTest {

    @Test
    fun `ticketPrice で月曜日の場合 (境界値も含む)`() {
        val isMonday = true

        /// -1
        assertEquals(
            "-1 (境界値) の場合",
            -1,
            ticketPrice(-1, isMonday)
        )

        /// 0 in 12
        assertEquals(
            "0 (境界値) の場合",
            15,
            ticketPrice(0, isMonday)
        )
        assertEquals(
            "7の場合",
            15,
            ticketPrice(7, isMonday)
        )
        assertEquals(
            "12 (境界値) の場合",
            15,
            ticketPrice(12, isMonday)
        )

        /// 13 in 60
        assertEquals(
            "13 (境界値) の場合",
            25,
            ticketPrice(13, isMonday)
        )
        assertEquals(
            "30 の場合",
            25,
            ticketPrice(30, isMonday)
        )
        assertEquals(
            "60 (境界値) の場合",
            25,
            ticketPrice(60, isMonday)
        )

        /// 61 in 100
        assertEquals(
            "61 (境界値) の場合",
            20,
            ticketPrice(61, isMonday)
        )
        assertEquals(
            "79 の場合",
            20,
            ticketPrice(79, isMonday)
        )
        assertEquals(
            "100 (境界値) の場合",
            20,
            ticketPrice(100, isMonday)
        )

        /// over 101
        assertEquals(
            "101 (境界値) の場合",
            -1,
            ticketPrice(101, isMonday)
        )
        assertEquals(
            "150 の場合",
            -1,
            ticketPrice(150, isMonday)
        )
    }

    @Test
    fun `ticketPrice で月曜日以外の場合`() {
        val isMonday = false

        /// -1
        assertEquals(
            "-1 (境界値) の場合",
            -1,
            ticketPrice(-1, isMonday)
        )

        /// 0 in 12
        assertEquals(
            "0 (境界値) の場合",
            15,
            ticketPrice(0, isMonday)
        )
        assertEquals(
            "7の場合",
            15,
            ticketPrice(7, isMonday)
        )
        assertEquals(
            "12 (境界値) の場合",
            15,
            ticketPrice(12, isMonday)
        )

        /// 13 in 60
        assertEquals(
            "13 (境界値) の場合",
            30,
            ticketPrice(13, isMonday)
        )
        assertEquals(
            "30 の場合",
            30,
            ticketPrice(30, isMonday)
        )
        assertEquals(
            "60 (境界値) の場合",
            30,
            ticketPrice(60, isMonday)
        )

        /// 61 in 100
        assertEquals(
            "61 (境界値) の場合",
            20,
            ticketPrice(61, isMonday)
        )
        assertEquals(
            "79 の場合",
            20,
            ticketPrice(79, isMonday)
        )
        assertEquals(
            "100 (境界値) の場合",
            20,
            ticketPrice(100, isMonday)
        )

        /// over 101
        assertEquals(
            "101 (境界値) の場合",
            -1,
            ticketPrice(101, isMonday)
        )
        assertEquals(
            "150 の場合",
            -1,
            ticketPrice(150, isMonday)
        )
    }
}