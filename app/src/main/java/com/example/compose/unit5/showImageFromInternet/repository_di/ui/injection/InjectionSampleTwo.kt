/// 依存性について
/// 依存性 その2
/// Car が使う Engine インスタンスを main関数で提供する

interface Engine {
    fun start()
}

class GasEngine: Engine {
    override fun start() {
       println("GasEngine started!!!")
    }
}

class Car(private val engine: Engine) {
    fun start(){
        engine.start()
    }
}

/// 例えばこんなクラスを追加したとしても、 Car クラスを変更する必要がないので変更容易性がある
class ElectricEngine: Engine {
    override fun start() {
        println("ElectricEngine started!!!")
    }
}

fun main() {
    val engine = GasEngine()
    val car = Car(engine)
    car.start()

    val electricEngine = ElectricEngine()
    val electricCar = Car(electricEngine)
    electricCar.start()
}