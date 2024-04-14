/// 依存性について

/// 依存性 その1

interface Engine {
    fun start()
}

class GasEngine: Engine {
    override fun start() {
        println("GasEngine started!!!")
    }
}

class Car {
    private val engine = GasEngine()

    fun start() {
        engine.start()
    }
}

fun main() {
    val car = Car()
    car.start()
}