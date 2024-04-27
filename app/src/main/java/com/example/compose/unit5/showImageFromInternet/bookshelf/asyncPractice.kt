package com.example.compose.unit5.showImageFromInternet.bookshelf

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        fetch()
    }
}


suspend fun fetch() {
    coroutineScope {
        runCatching {
            Thread.sleep(2000)
            repeat(10) { println(it) }
        }
        println("hello")
        launch {
            repeat(10) { println(it) }
            Thread.sleep(2000)
        }
        println("world")
        launch {
            Thread.sleep(2000)
            repeat(10) { println(it) }
        }
        println("hello hiroki!")
    }
}