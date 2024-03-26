package com.example.compose.buildingappui.interactingwiehuiandstate.codelab

import com.example.compose.unit2.buildingappui.interactingwiehuiandstate.codelab.calculateTip
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat


class IntroToStateInComposeKtTest {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}