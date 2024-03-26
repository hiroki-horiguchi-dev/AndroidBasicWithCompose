package com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.temperature

fun main() {
    // Fill in the code.
    printFinalTemperature(
        initialMeasurement = 27.0,
        initialUnit = "Celsius",
        finalUnit = "Fahrenheit",
        conversionFormula = convertCelsiusToFahrenheit
    )
    printFinalTemperature(
        initialMeasurement = 350.0,
        initialUnit = "Kelvin",
        finalUnit = "Celsius",
        conversionFormula = convertKelvinToCelsius
    )
    printFinalTemperature(
        initialMeasurement = 10.0,
        initialUnit = "Fahrenheit",
        finalUnit = "Kelvin",
        conversionFormula = convertFahrenheitToKelvin
    )
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

val convertCelsiusToFahrenheit: (Double) -> Double = {
    (1.8 * it) + 32
}

val convertKelvinToCelsius: (Double) -> Double = {
    it - 273.15
}

val convertFahrenheitToKelvin: (Double) -> Double = {
    ((it - 32) * 5/9) + 273.15
}
