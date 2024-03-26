package com.example.compose.kotlinBasic.codelab.exercises

import junit.framework.Assert.assertEquals
import org.junit.Test


class MobileKtTest {

    @Test
    fun printNotificationSummary() {
        assertEquals(
            "You have 0 notifications.",
            com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile.printNotificationSummary(
                0
            )
        )
        assertEquals(
            "You have 50 notifications.",
            com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile.printNotificationSummary(
                50
            )
        )
        assertEquals(
            "You have 99 notifications.",
            com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile.printNotificationSummary(
                99
            )
        )
        assertEquals(
            "Your phone is blowing up! You have 99+ notifications.",
            com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile.printNotificationSummary(
                100
            )
        )
        assertEquals(
            "Your phone is blowing up! You have 99+ notifications.",
            com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile.printNotificationSummary(
                120
            )
        )
    }
}