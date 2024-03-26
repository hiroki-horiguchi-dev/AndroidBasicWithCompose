package com.example.compose.kotlinBasic.codelab.exercises

import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.uniqueauction.Bid
import org.junit.Before
import org.junit.Test
import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.uniqueauction.auctionPrice
import junit.framework.Assert.assertEquals


class UniqueauctionKtTest {

    lateinit var winningBid: Bid

    @Before
    fun setUp() {
        winningBid = Bid(5000, "Private Collector")
    }


    @Test
    fun auctionPrice() {

        assertEquals(
            5000,
            auctionPrice(
                winningBid,
                2000
            )
        )

        assertEquals(
            3000,
            auctionPrice(
                null,
                3000
            )
        )

    }
}