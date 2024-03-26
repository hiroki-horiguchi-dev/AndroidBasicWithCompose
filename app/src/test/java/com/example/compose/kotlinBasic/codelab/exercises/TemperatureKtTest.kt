package com.example.compose.kotlinBasic.codelab.exercises

import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.temperature.convertCelsiusToFahrenheit
import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.temperature.convertKelvinToCelsius
import com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.temperature.convertFahrenheitToKelvin


class TemperatureKtTest {

    @Test
    fun convertCelsiusToDegrees() {
        val initialTemp = 27.0
        val finalTemp = "80.60"
        assertEquals(
            finalTemp,
            String.format("%.2f",
                convertCelsiusToFahrenheit(
                    initialTemp
                )
            )
        )
    }

    @Test
    fun convertKelvinToCelsius() {
        val initialTemp = 350.0
        val finalTemp = "76.85"
        assertEquals(
            finalTemp,
            String.format("%.2f",
                convertKelvinToCelsius(
                    initialTemp
                )
            )
        )
    }

    @Test
    fun convertFahrenheitToKelvin() {
        val initialTemp = 10.0
        val finalTemp = "260.93"
        assertEquals(
            finalTemp,
            String.format("%.2f",
                convertFahrenheitToKelvin(
                    initialTemp
                )
            )
        )
    }
}