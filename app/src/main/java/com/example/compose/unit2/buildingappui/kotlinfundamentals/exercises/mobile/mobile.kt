package com.example.compose.unit2.buildingappui.kotlinfundamentals.exercises.mobile

fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    println(printNotificationSummary(morningNotification))
    println(printNotificationSummary(eveningNotification))
}

fun printNotificationSummary(numberOfMessages: Int): String {
    return when (numberOfMessages) {
        in 0..99 -> "You have $numberOfMessages notifications."
        else -> "Your phone is blowing up! You have 99+ notifications."
    }
}
