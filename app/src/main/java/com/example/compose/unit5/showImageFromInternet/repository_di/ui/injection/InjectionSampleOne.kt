package com.example.compose.unit5.showImageFromInternet.repository_di.ui.injection
/// 依存性について
/// 依存性 その1
/// Car が使う Engine インスタンスは Car クラスが自前で用意する
private interface Engine {
    fun start()
}

private class GasEngine: Engine {
    override fun start() {
        println("GasEngine started!!!")
    }
}

private class Car {
    private val engine = GasEngine()

    fun start() {
        engine.start()
    }
}

fun main() {
    val car = Car()
    car.start()
}